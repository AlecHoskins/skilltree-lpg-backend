package com.alechoskins.skilltreelpgbackend.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public String healthCheck() {
        return "";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, world secured!";
    }
}
