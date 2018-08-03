package com.vk.demo.api2.controllers.mocks;

import com.vk.demo.api2.model.Gadget;
import com.vk.demo.api2.repositories.GadgetRepository;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class GadgetRepositoryMock implements GadgetRepository {

    private List<Gadget> mockGadgetList;

    public GadgetRepositoryMock(){}
    public GadgetRepositoryMock(List<Gadget> gadgetList) {
        this.mockGadgetList = gadgetList;
    }

    @Override
    public <S extends Gadget> Mono<S> save(S s) {
        return Mono.just(s);
    }

    @Override
    public <S extends Gadget> Flux<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public <S extends Gadget> Flux<S> saveAll(Publisher<S> publisher) {
        return null;
    }

    @Override
    public Mono<Gadget> findById(String s) {
        return null;
    }

    @Override
    public Mono<Gadget> findById(Publisher<String> publisher) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(String s) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(Publisher<String> publisher) {
        return null;
    }

    @Override
    public Flux<Gadget> findAll() {
        return mockGadgetList != null ? Flux.fromIterable(mockGadgetList) : null;
    }

    @Override
    public Flux<Gadget> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public Flux<Gadget> findAllById(Publisher<String> publisher) {
        return null;
    }

    @Override
    public Mono<Long> count() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(String s) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Publisher<String> publisher) {
        return null;
    }

    @Override
    public Mono<Void> delete(Gadget gadget) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll(Iterable<? extends Gadget> iterable) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll(Publisher<? extends Gadget> publisher) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll() {
        return null;
    }
}
