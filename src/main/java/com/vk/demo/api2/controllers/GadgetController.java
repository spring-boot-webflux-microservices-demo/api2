package com.vk.demo.api2.controllers;

import com.google.common.net.MediaType;
import com.vk.demo.api2.model.Gadget;
import com.vk.demo.api2.repositories.GadgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

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

    @GetMapping("/findGadgetById/{id}")
    public Mono<ResponseEntity<Gadget>> findGadgetById(@PathVariable String id) {
        return gadgetRepository.findById(id)
                .flatMap(g -> Mono.just(ResponseEntity.ok().body(g)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping("/saveGadget")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Mono<Gadget> saveGadget(@RequestBody Gadget gadget) {
        return gadgetRepository.save(gadget);
    }

    @PutMapping("/updateGadget/{oldGadgetId}")
    public @ResponseBody
    Mono<ResponseEntity<Gadget>> updateGadget(@PathVariable String oldGadgetId, @RequestBody Gadget newGadget) {
        newGadget.setId(oldGadgetId);
        return gadgetRepository.findById(oldGadgetId)
                .flatMap(d -> gadgetRepository.deleteById(d.getId()).then(Mono.just(newGadget)
                        .map(ng -> new Gadget(oldGadgetId, ng.getType(), ng.getSpecifications()))
                        .flatMap(gadgetRepository::save)
                        .flatMap(a -> Mono.just(ResponseEntity.created(URI.create("/api2/updatedGadget/" + a.getId())).body(a)))))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/deleteGadget/{gadgetId}")
    public @ResponseBody
    Mono<ResponseEntity<Object>> deleteGadget(@PathVariable String gadgetId) {
        return gadgetRepository.findById(gadgetId)
                .flatMap(a -> gadgetRepository.delete(a)
                        .then(Mono.just(ResponseEntity.noContent().build())))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
