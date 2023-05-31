package com.microservices.niusm.ApiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/teacher-service")
    public Mono<String> teacherServiceFallBack() {
        return Mono.just("Teacher service is not available right now. Try again later!");
    }

    @GetMapping("/address-service")
    public Mono<String> addressServiceFallBack() {
        return Mono.just("Address Service is not available right now. Try again later!");
    }

    @GetMapping("/subject-service")
    public Mono<String> subjectServiceFallBack() {
        return Mono.just("Subject Service is not available right now. Try again later!");
    }
}