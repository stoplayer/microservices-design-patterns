package org.example.communicationpatterns.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiGatewayService {

    private final WebClient.Builder webClientBuilder;

    public ApiGatewayService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<String> routeRequest(String path, String requestBody) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/" + path)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("Error processing request: " + e.getMessage()));
    }

    public Mono<String> getServiceHealth(String serviceUrl) {
        return webClientBuilder.build()
                .get()
                .uri(serviceUrl + "/health")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Service Unavailable");
    }
}
