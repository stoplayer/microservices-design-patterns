package org.example.communicationpatterns.gateway.controller;

import org.example.communicationpatterns.gateway.service.ApiGatewayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/gateway")
public class ApiGatewayController {

    private final ApiGatewayService apiGatewayService;

    public ApiGatewayController(ApiGatewayService apiGatewayService) {
        this.apiGatewayService = apiGatewayService;
    }

    @PostMapping("/{path}")
    public Mono<ResponseEntity<String>> handleRequest(
            @PathVariable String path,
            @RequestBody String requestBody) {
        return apiGatewayService.routeRequest(path, requestBody)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body(e.getMessage())));
    }

    @GetMapping("/health/{service}")
    public Mono<ResponseEntity<String>> checkServiceHealth(@PathVariable String service) {
        return apiGatewayService.getServiceHealth("http://localhost:808" + service)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Service unavailable")));
    }
}
