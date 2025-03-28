package com.microcompany.oauth2resourceserver.controller;

import com.microcompany.oauth2resourceserver.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class AccountController {

    @GetMapping("/account")
    public List<Account> getAccounts() {
        return Arrays.asList(
                new Account[]{
                        new Account(1L, "Personal", Date.valueOf("2024-04-10"), 1000, 1L),
                        new Account(2L, "Company", Date.valueOf("2023-04-10"), 2000, 2L),
                        new Account(3L, "Personal", Date.valueOf("2022-04-10"), 3000, 3L),
                        new Account(4L, "Company", Date.valueOf("2021-04-10"), 4000, 1L),
                }
        );
    }
}