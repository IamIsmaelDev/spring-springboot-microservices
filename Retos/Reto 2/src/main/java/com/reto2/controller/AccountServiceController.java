package com.reto2.controller;

import com.reto2.exception.AccountNotfoundException;
import com.reto2.model.Account;
import com.reto2.persistence.AccountRepository;
import com.reto2.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountServiceController implements IAccountServiceController{

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public ResponseEntity getAllAccountsByCustomer(Long id) {
        List<Account> accountList = accountService.getAccountByOwnerId(id);
        return ResponseEntity.status(HttpStatus.OK.value()).body(accountList);
    }

    @Override
    public ResponseEntity getAccountByCustomer(Long id, Long oid) {
        Account account = accountService.getAccount(id);
        if(account.getOwnerId().equals(oid))
            return ResponseEntity.status(HttpStatus.OK.value()).body(account);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Error");
    }

    @Override
    public ResponseEntity createAccount(Account account, Long oid) {
        account.setOwnerId(oid);
        Account account1 = accountService.create(account);
        return ResponseEntity.status(HttpStatus.OK.value()).body(account);
    }

    @Override
    public ResponseEntity updateAccount(Account account, Long oid) {
        // Busca una cuenta en BD que corresponda con la que se quiere actualizar y la guarda
        Account newAccount = accountRepository.findById(account.getId()).orElseThrow(() -> new AccountNotfoundException(account.getId()));


        if(newAccount.getOwnerId().equals(oid)) {
            accountService.updateAccount(account.getId(), account);
            return ResponseEntity.status(HttpStatus.OK.value()).body(account);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("No existe la cuenta para este usuario");
        }
    }

}
