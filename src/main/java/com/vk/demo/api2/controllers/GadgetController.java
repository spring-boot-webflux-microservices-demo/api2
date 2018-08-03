package com.vk.demo.api2.controllers;

import com.vk.demo.api2.model.Gadget;
import com.vk.demo.api2.repositories.GadgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServerResponse;

import javax.servlet.http.HttpServletResponse;

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

    @PutMapping("/updateGadget/{oldGadgetId}")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Mono<Gadget> updateGadget(@PathVariable String oldGadgetId, @RequestBody Gadget newGadget) {
        newGadget.setId(oldGadgetId);
        Mono<Gadget> updated = gadgetRepository.deleteById(oldGadgetId).then(Mono.just(newGadget)
                .flatMap(gadgetRepository::save))
                .switchIfEmpty(Mono.empty());

        return updated;
    }
}
