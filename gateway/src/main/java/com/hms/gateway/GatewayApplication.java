package com.hms.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
//		return routeLocatorBuilder.routes()
//				.route("patient-service", r -> r.path("/patient/**")
//						.filters(f -> f.rewritePath("/patient/(?<segment>.*)", "/${segment}")
//								.filter((exchange, chain) -> {
//									System.out.println("Request Time: " + LocalDateTime.now());
//									return chain.filter(exchange);
//								})
//						)
//						.uri("lb://PATIENT-SERVICE")).build();
//	}
}
