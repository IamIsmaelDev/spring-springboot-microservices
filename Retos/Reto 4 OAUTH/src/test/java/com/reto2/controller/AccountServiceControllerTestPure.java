package com.reto2.controller;

import com.reto2.model.Account;
import com.reto2.persistence.AccountRepository;
import com.reto2.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: uncomment and implement methods
@ExtendWith(MockitoExtension.class) // más puro
@MockitoSettings(strictness = Strictness.LENIENT) // más puro
class AccountServiceControllerTestPure {

    @BeforeEach
    public void setUp() {

        List<Account> accountList = List.of(
                new Account("Personal", null, 1200, 1L),
                new Account("Company", null, 1500, 1L),
                new Account("Personal", null, 500, 1L)
        );

        Mockito.when(accountRepositoryMock.findByOwnerId(1L)).thenReturn(accountList);
        Mockito.when(accountRepositoryMock.findByOwnerId(99L)).thenReturn(null);

        Mockito.when(accountRepositoryMock.save(Mockito.any(Account.class)))
                .thenAnswer(elem -> {
                    Account ac = (Account) elem.getArguments()[0];
                    return ac;
                });
    }

    @InjectMocks // más puro
    private AccountServiceController controller;

    @Mock
    private AccountService accountServiceMock;

    @Mock
    AccountRepository accountRepositoryMock;


    @Test
    void givenIdCustomerGettAllAccountsThenOK() {
        ResponseEntity<List<Account>> response = controller.getAllAccountsByCustomer(1L);
        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isNotNull();
    }

     @Test
    void givenAccountIdOwnerIdNoExistThenBadRequest(){

        ResponseEntity response = controller.deleteAccountsByUser(1L);

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());

    }

}