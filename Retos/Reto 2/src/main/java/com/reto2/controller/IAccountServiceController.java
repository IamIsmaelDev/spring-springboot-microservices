package com.reto2.controller;

import com.reto2.model.Account;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/default")
public interface IAccountServiceController {

    // PUNTO 1 -> RESTer : http://localhost:9090/account/3/3
    @Operation(summary = "Obtener una cuenta de un cliente", description = "Método para solicitar una cuenta de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta obtenida con éxito"),
            @ApiResponse(responseCode = "400", description = "La cuenta indicada no existe para este cliente")

    })
    @GetMapping(value = "/{id}/customer/{oid}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAccountByCustomer(
            @Parameter(name = "account_id", description = "introduce el id de la cuenta",example = "1")
            @PathVariable("id") Long id,
            @Parameter(name = "owner_id", description = "introduce el id de un cliente",example = "1")
            @PathVariable("oid") Long oid);

    // Punto 2 -> RESTer : http://localhost:9090/account/3
    @GetMapping(value = "/customer/{id}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllAccountsByCustomer(@PathVariable("id") Long id);

    // Punto 3 -> RESTer : http://localhost:9090/account/1/create
    // HEADERS : Name(Content-Type) Value(Application/json)
    // BODY: { "type":"ahorro", "balance":500}
    @PostMapping(value = "/customer/{oid}/create", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity createAccount(@Valid @RequestBody Account account, @PathVariable Long oid);

    // RESTer : http://localhost:9090/account/1/update
    // HEADERS : Name(Content-Type) Value(Application/json)
    // BODY: {"id": 1,"type":"j","balance":50}
    @PutMapping(value = "/customer/{oid}/update")
    ResponseEntity updateAccount(@RequestBody Account account, @PathVariable Long oid);

    // RESTer : http://localhost:9090/account/1/1
    @DeleteMapping(value = "/{aid}/customer/{oid}")
    ResponseEntity deleteAccount(@PathVariable Long aid, @PathVariable Long oid);

    // Punto 4 -> RESTer : http://localhost:9090/account/3/deleteAllAccounts
    @PutMapping(value = "/{aid}/customer/{oid}/addBalance")
    ResponseEntity addMoneyToBalance(@RequestBody int balance, @PathVariable Long aid, @PathVariable Long oid);

    // Punto 5 -> RESTer : http://localhost:9090/account/3/deleteAllAccounts
    @PutMapping(value = "/{aid}/customer/{oid}/takeBalance")
    ResponseEntity takeMoneyToBalance(@RequestBody int balance, @PathVariable Long aid, @PathVariable Long oid);

    // Punto 6 -> RESTer : http://localhost:9090/account/3/deleteAllAccounts
    @DeleteMapping(value = "/customer/{oid}/deleteAllAccounts")
    ResponseEntity deleteAccountsByUser(@PathVariable Long pid);

    @GetMapping(value = "/customer/{oid}/quantity/{money}/loan", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity loan(@PathVariable Long oid, @PathVariable Integer money);

    // BONUS -> RESTer : http://localhost:9090/account/3/deleteAllAccounts
    @PutMapping(value = "/{aid}/customer/{oid}/takeBalanceAllAccounts")
    ResponseEntity takeMoneyToBalanceAllAccounts(@RequestBody int balance, @PathVariable Long aid, @PathVariable Long oid);

    // DTO
    @GetMapping(value = "/DTO/{id}/customer/{oid}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAccountByCustomerDTO(
            @Parameter(name = "account_id", description = "introduce el id de la cuenta",example = "1")
            @PathVariable("id") Long id,
            @Parameter(name = "owner_id", description = "introduce el id de un cliente",example = "1")
            @PathVariable("oid") Long oid);
}
