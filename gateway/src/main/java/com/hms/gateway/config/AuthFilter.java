package com.hms.gateway.config;

import com.hms.gateway.security.JwtTokenProvider;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

// import java.util.Objects;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final JwtTokenProvider jwtTokenProvider;

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
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new RuntimeException("Missing or invalid Authorization header");
            }

            String token = authHeader.substring(7); // Loại bỏ tiền tố "Bearer "

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