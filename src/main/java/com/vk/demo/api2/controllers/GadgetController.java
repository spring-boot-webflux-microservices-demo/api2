package com.vk.demo.api2.controllers;

import com.vk.demo.api2.model.Gadget;
import com.vk.demo.api2.repositories.GadgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api2")
public class GadgetController {

    private GadgetRepository gadgetRepository;

    @Autowired
    public GadgetController(GadgetRepository gadgetRepository) {
        this.gadgetRepository = gadgetRepository;
    }

    @GetMapping("/findAllGadgets")
    public Flux<Gadget> findAllGadgets() {
        return gadgetRepository.findAll();
    }

    @PostMapping("/saveGadget")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Mono<Gadget> saveGadget(@RequestBody Gadget gadget) {
        return gadgetRepository.save(gadget);
    }

}
