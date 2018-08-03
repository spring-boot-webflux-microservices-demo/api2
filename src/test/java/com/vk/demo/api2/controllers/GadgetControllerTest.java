package com.vk.demo.api2.controllers;

import com.vk.demo.api2.controllers.mocks.GadgetRepositoryMock;
import com.vk.demo.api2.repositories.GadgetRepository;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

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
                .expectStatus()
                .isOk()
                .expectBody().json(EMPTY_RAW_JSON_LIST);
    }
}