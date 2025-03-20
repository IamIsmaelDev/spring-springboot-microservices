package com.reto2.controller;

import com.reto2.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/default")
public interface IAccountServiceController {

    // PUNTO 1
    @GetMapping(value = "/{id}/{oid}")
    ResponseEntity getAccountByCustomer(@PathVariable("id") Long id, @PathVariable("oid") Long oid);

    // Punto 2
    @GetMapping(value = "/{id}")
    ResponseEntity getAllAccountsByCustomer(@PathVariable("id") Long id);

    @PostMapping(value = "/{oid}/create")
    ResponseEntity createAccount(@RequestBody Account account, @PathVariable Long oid);


    @PutMapping(value = "/{oid}/update")
    ResponseEntity updateAccount(@RequestBody Account account, @PathVariable Long oid);

    /*
    @DeleteMapping(value = "/{pid}")
    ResponseEntity deleteProduct(@PathVariable Long pid);
    */


}
