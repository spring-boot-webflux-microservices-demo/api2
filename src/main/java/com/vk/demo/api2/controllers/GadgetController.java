package com.vk.demo.api2.controllers;

import com.vk.demo.api2.model.Gadget;
import com.vk.demo.api2.repositories.GadgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api2")
public class GadgetController {

    private GadgetRepository gadgetRepository;

    @Autowired
    public GadgetController(GadgetRepository gadgetRepository) {
        this.gadgetRepository = gadgetRepository;
    }

    @GetMapping(value = "/findAllGadgets")
    public Flux<Gadget> findAllGadgets() {
        return gadgetRepository.findAll();
    }

}
