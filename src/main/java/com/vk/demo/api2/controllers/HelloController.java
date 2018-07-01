package com.vk.demo.api2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from api2";
    }
}
