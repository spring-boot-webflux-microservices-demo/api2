package com.vk.demo.api2.controllers;

import com.vk.demo.api2.controllers.mocks.GadgetMock;
import com.vk.demo.api2.controllers.mocks.GadgetRepositoryMock;
import com.vk.demo.api2.model.Gadget;
import com.vk.demo.api2.repositories.GadgetRepository;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@WebFluxTest
@SpringBootTest
public class GadgetControllerTest {

    private static final String EMPTY_RAW_JSON_LIST = "[]";
    private WebTestClient webTestClient;
    private GadgetRepository gadgetRepository;

    @Test
    public void findAllGadgets_whenNoGadgets_returnEmptyListWithHttpStatus200() {
        gadgetRepository = new GadgetRepositoryMock(Collections.emptyList());
        webTestClient = WebTestClient.bindToController(new GadgetController(gadgetRepository)).build();
        webTestClient.get().uri("/api2/findAllGadgets")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(EMPTY_RAW_JSON_LIST);
    }

    @Test
    public void saveGadget() {
        Gadget gadget = GadgetMock.createGadget();
        gadgetRepository = new GadgetRepositoryMock(Collections.singletonList(gadget));
        webTestClient = WebTestClient.bindToController(new GadgetController(gadgetRepository)).build();
        webTestClient.post().uri("/api2/saveGadget")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(gadget), Gadget.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json(GadgetMock.createGadgetRawJsonObject());
    }

    @Test
    public void updateGadget_whenGadgetExist_updatedWithHttpStatus201() {
        Gadget oldGadget = GadgetMock.createGadget("oldType", "oldSpecs");
        Gadget newGadget = GadgetMock.createGadget("updatedType", "updatedSpecs");
        String updatedGadgetRawJson = GadgetMock.createGadgetRawJsonObject(
                new Gadget(oldGadget.getId(), newGadget.getType(), newGadget.getSpecifications()));
        gadgetRepository = new GadgetRepositoryMock(Collections.singletonList(oldGadget));
        webTestClient = WebTestClient.bindToController(new GadgetController(gadgetRepository)).build();
        webTestClient.put().uri("/api2/updateGadget/" + oldGadget.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newGadget), Gadget.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json(updatedGadgetRawJson);
    }

    @Test
    public void updateGadget_whenGadgetNotExist_returnHttpStatus404() {
        Gadget oldGadget = GadgetMock.createGadget("oldType", "oldSpecs");
        Gadget newGadget = GadgetMock.createGadget("updatedType", "updatedSpecs");
        new Gadget(oldGadget.getId(), newGadget.getType(), newGadget.getSpecifications());
        gadgetRepository = new GadgetRepositoryMock(Collections.emptyList());
        webTestClient = WebTestClient.bindToController(new GadgetController(gadgetRepository)).build();
        webTestClient.put().uri("/api2/updateGadget/" + oldGadget.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newGadget), Gadget.class)
                .exchange()
                .expectStatus().isNotFound();
    }
}