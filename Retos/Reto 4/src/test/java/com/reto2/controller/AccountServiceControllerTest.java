package com.reto2.controller;

import com.reto2.exception.AccountNotfoundException;
import com.reto2.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

// TODO: uncomment and implement methods
@SpringBootTest
@Sql(value = "classpath:testing.sql")
class AccountServiceControllerTest {

    @Autowired
    private AccountServiceController controller;

    @Test
    void givenIdCustomerGettAllAccountsThenOK() {
        ResponseEntity<List<Account>> response = controller.getAllAccountsByCustomer(1L);
        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isNotNull();
    }

     @Test
    void givenAccountIdOwnerIdNoExistThenBadRequest(){

        ResponseEntity response = controller.getAccountByCustomer(1L,100L);

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void givenAccountIdAndAccountUpdateThenBadRequest(){

        Account newAcount = new Account();
        newAcount.setId(5L);
        newAcount.setType("Personal");
        newAcount.setOpeningDate(null);
        newAcount.setBalance(9999);
        newAcount.setOwnerId(1L);

        ResponseEntity response = controller.updateAccount(newAcount, 99L);

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void givenAccountIdOwnerIdThenOK(){

        ResponseEntity response = controller.getAccountByCustomerDTO(1L, 1L);
        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isNotNull();

    }

}