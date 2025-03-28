package com.reto2.services;



import com.reto2.model.Account;

import java.util.List;

public interface IAccountService {
    Account create(Account account);

    List<Account> getAccounts();

    Account getAccount(Long id);

    List<Account> getAccountByOwnerId(Long ownerId);

    Account updateAccount(Long id, Account account);

    Account addBalance(Long id, int amount, Long ownerId);

    Account withdrawBalance(Long id, int amount, Long ownerId);

    void delete(Long id);

    void deleteAccountsUsingOwnerId(Long ownerId);

    boolean withdrawBalanceAllAcounts(Long id, int amount, Long ownerId);
}
