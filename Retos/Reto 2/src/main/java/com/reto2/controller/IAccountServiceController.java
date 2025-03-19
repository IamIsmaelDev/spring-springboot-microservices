package com.reto2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/default")
public interface IAccountServiceController {

    // PUNTO 1
    @GetMapping(value = "/{id}/{oid}")
    ResponseEntity getAccountByCustomer(@PathVariable("id") Long id, @PathVariable("oid") Long oid);

    // Punto 2
    @GetMapping(value = "/{id}")
    ResponseEntity getAllAccountsByCustomer(@PathVariable("id") Long id);





}
