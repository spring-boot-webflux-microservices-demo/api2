package com.vk.demo.api2.controllers;

import com.vk.demo.api2.model.Pojo2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin("*")
@RequestMapping("/api2")
public class HelloController {

    @GetMapping("/hello")
    public Flux<Pojo2> hello() {
        return Flux.just(
                new Pojo2("1", "api2 awesome"),
                new Pojo2("2", "api2 it"),
                new Pojo2("3", "api2 works!")
        );
    }
}
