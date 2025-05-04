package com.hms.gateway.config;

import com.hms.gateway.security.JwtTokenProvider;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

// import java.util.Objects;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final JwtTokenProvider jwtTokenProvider;

    private static final List<String> EXEMPT_PATHS = List.of(
        "/api/v1/doctors",
        "/auth-service/v3/api-docs",
        "/auth-service/swagger-ui.html",
        "/patient-service/v3/api-docs",
        "/patient-service/swagger-ui.html",
        "/staff-service/v3/api-docs",
        "/staff-service/swagger-ui.html");

    public AuthFilter(JwtTokenProvider jwtTokenProvider) {
        super(Config.class);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public static class Config {
        // Empty class as no specific configuration is needed
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            String method = exchange.getRequest().getMethod().name();

            if (EXEMPT_PATHS.contains(path) && "POST".equalsIgnoreCase(method)) {
                return chain.filter(exchange);
            }
            
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new RuntimeException("Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7);

            if (!jwtTokenProvider.validateToken(token)) {
                throw new RuntimeException("Invalid JWT token");
            }

            String username = jwtTokenProvider.getUsername(token);

            if (username == null || username.isEmpty()) {
                throw new RuntimeException("Invalid JWT token: Missing username");
            }

            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("X-auth-username", username)
                    .build();

            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}