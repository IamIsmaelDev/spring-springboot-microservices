package com.reto2.service;

import com.reto2.exception.AccountNotfoundException;
import com.reto2.model.Account;
import com.reto2.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(value = "classpath:testing.sql")
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void givenDataCreateAccount(){

        Account newAcount = new Account();
        newAcount.setType("Personal");
        newAcount.setOpeningDate(null);
        newAcount.setBalance(9999);
        newAcount.setOwnerId(1L);

        Account accountCreated = accountService.create(newAcount);

        assertThat(accountCreated).isNotNull();
    }

    @Test
    void givenAccountIdNoExistThenException(){

        assertThatExceptionOfType(AccountNotfoundException.class).isThrownBy(() -> {
            Account account = accountService.getAccount(99L);
        });
    }

    @Test
    void givenAccountIdAndAccountUpdateThenException(){

        Account newAcount = new Account();
        newAcount.setType("Personal");
        newAcount.setOpeningDate(null);
        newAcount.setBalance(9999);
        newAcount.setOwnerId(1L);

        Account accountCreated = accountService.create(newAcount);

        assertThatExceptionOfType(AccountNotfoundException.class).isThrownBy(() -> {
            Account accountUpdate = accountService.updateAccount(100L, accountCreated);
        });

    }

    @Test
    void givenOwnerIdDeleteAllAccounts(){

        accountService.deleteAccountsUsingOwnerId(3L);
        List<Account> accountList = accountService.getAccountByOwnerId(3L);
        assertThat(accountList.isEmpty());

    }

}
