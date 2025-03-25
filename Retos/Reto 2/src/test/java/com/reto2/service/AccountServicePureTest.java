package com.reto2.service;

import com.reto2.exception.AccountNotfoundException;
import com.reto2.model.Account;
import com.reto2.persistence.AccountRepository;
import com.reto2.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
//@Import(ProductsService.class)
//@ExtendWith(MockitoExtension.class) // más puro
//@MockitoSettings(strictness = Strictness.LENIENT) // más puro
public class AccountServicePureTest {

    @TestConfiguration
    static class AccountServiceConf{
        @Bean
        public AccountService getAccountServiceBean(){
            return new AccountService();
        }
    }

    //@InjectMocks // más puro
    @Autowired
    AccountService accService;

    //    @Mock // más puro
    @MockBean
    AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {

        List<Account> accountList = List.of(
                new Account("Personal", null, 1200, 1L),
                new Account("Company", null, 1500, 1L),
                new Account("Personal", null, 500, 1L)
        );

        Mockito.when(accountRepository.findByOwnerId(1L)).thenReturn(accountList);
        Mockito.when(accountRepository.findByOwnerId(99L)).thenReturn(null);

        Mockito.when(accountRepository.save(Mockito.any(Account.class)))
                .thenAnswer(elem -> {
                    Account ac = (Account) elem.getArguments()[0];
                    return ac;
                });
    }

    @Test
    void givenOwnerIdWhenSearchByCustomerNotNull() {
        List<Account> accountList = accService.getAccountByOwnerId(1L);
        assertThat(accountList).isNotNull();
        assertThat(accountList.size()).isGreaterThan(0);
    }

    @Test
    void givenAccountIdNoExistThenException() {
        assertThatExceptionOfType(AccountNotfoundException.class).isThrownBy(() -> {
            Account account = accService.getAccount(99L);
        });
    }

}
