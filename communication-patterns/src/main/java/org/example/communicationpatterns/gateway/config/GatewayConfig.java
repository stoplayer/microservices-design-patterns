package org.example.communicationpatterns.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service1_route", r -> r
                        .path("/service1/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .addRequestHeader("X-Gateway-Token", "internal-token"))
                        .uri("http://localhost:8081"))
                .route("service2_route", r -> r
                        .path("/service2/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .addRequestHeader("X-Gateway-Token", "internal-token"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
