package com.reto2.controller;

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
        account.getOwnerId().equals(oid);
        return ResponseEntity.status(HttpStatus.OK.value()).body(account);
    }
}
