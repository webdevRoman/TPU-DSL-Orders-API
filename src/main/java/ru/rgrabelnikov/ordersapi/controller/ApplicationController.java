package ru.rgrabelnikov.ordersapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders-api/application")
public class ApplicationController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping
    public String getName() {
        return applicationName;
    }
}
