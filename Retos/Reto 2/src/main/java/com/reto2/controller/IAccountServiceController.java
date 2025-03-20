package com.reto2.controller;

import com.reto2.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/default")
public interface IAccountServiceController {

    // PUNTO 1 -> RESTer : http://localhost:9090/account/3/3
    @GetMapping(value = "/{id}/{oid}")
    ResponseEntity getAccountByCustomer(@PathVariable("id") Long id, @PathVariable("oid") Long oid);

    // Punto 2 -> RESTer : http://localhost:9090/account/3
    @GetMapping(value = "/{id}")
    ResponseEntity getAllAccountsByCustomer(@PathVariable("id") Long id);

    // Punto 3 -> RESTer : http://localhost:9090/account/1/create
    // HEADERS : Name(Content-Type) Value(Application/json)
    // BODY: { "type":"ahorro", "balance":500}
    @PostMapping(value = "/{oid}/create")
    ResponseEntity createAccount(@RequestBody Account account, @PathVariable Long oid);

    // RESTer : http://localhost:9090/account/1/update
    // HEADERS : Name(Content-Type) Value(Application/json)
    // BODY: {"id": 1,"type":"j","balance":50}
    @PutMapping(value = "/{oid}/update")
    ResponseEntity updateAccount(@RequestBody Account account, @PathVariable Long oid);

    // RESTer : http://localhost:9090/account/1/1
    @DeleteMapping(value = "/{oid}/{aid}")
    ResponseEntity deleteAccount(@PathVariable Long pid, @PathVariable Long aid);

    // Punto 4 -> RESTer : http://localhost:9090/account/3/deleteAllAccounts
    @PutMapping(value = "/{aid}/{oid}/addBalance")
    ResponseEntity addMoneyToBalance(@RequestBody int balance, @PathVariable Long aid, @PathVariable Long oid);

    // Punto 5 -> RESTer : http://localhost:9090/account/3/deleteAllAccounts
    @PutMapping(value = "/{aid}/{oid}/takeBalance")
    ResponseEntity takeMoneyToBalance(@RequestBody int balance, @PathVariable Long aid, @PathVariable Long oid);

    // Punto 6 -> RESTer : http://localhost:9090/account/3/deleteAllAccounts
    @DeleteMapping(value = "/{oid}/deleteAllAccounts")
    ResponseEntity deleteAccountsByUser(@PathVariable Long pid);

    @GetMapping(value = "/{oid}/{money}/loan")
    ResponseEntity loan(@PathVariable Long oid, @PathVariable Integer money);
}
