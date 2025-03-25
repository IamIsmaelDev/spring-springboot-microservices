package com.reto2.persistence;

import com.reto2.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.reto2"})
@AutoConfigureTestEntityManager
@Sql(value = "classpath:testing.sql")
public class AccountRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findByOwnerId(){
        // given
        Account newAcount = new Account();
        Date current_Date = new Date();
        newAcount.setType("Personal");
        newAcount.setOpeningDate(current_Date);
        newAcount.setBalance(9999);
        newAcount.setOwnerId(1L);
        entityManager.persist(newAcount);
        entityManager.flush();

        // when
        List<Account> accountList = accountRepository.findByOwnerId(1L);
        logger.info("Accounts:" + accountList);

        // then
        assertNotNull(accountList);

        assertThat(accountList.size())
                .isGreaterThan(0);
    }

}
