package com.vk.demo.api2.repositories;

import com.vk.demo.api2.model.Gadget;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GadgetRepository extends ReactiveCrudRepository<Gadget, String> {

}
