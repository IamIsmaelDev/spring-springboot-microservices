package com.reto2.persistence;


import com.reto2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByOwnerId(Long OwnerId);
}
