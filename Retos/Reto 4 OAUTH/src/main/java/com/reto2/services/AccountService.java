package com.reto2.services;


import com.reto2.exception.AccountNotfoundException;
import com.reto2.model.Account;
import com.reto2.model.Customer;
import com.reto2.persistence.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountService implements IAccountService {
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        Date current_Date = new Date();
        account.setOpeningDate(current_Date);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotfoundException(id));
        Customer owner = null; // Will be gotten from user service
        account.setOwner(owner);
        return account;
    }

    @Override
    public List<Account> getAccountByOwnerId(Long ownerId) {
        return accountRepository.findByOwnerId(ownerId);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Account newAccount = accountRepository.findById(id).orElseThrow(() -> new AccountNotfoundException(id));
        newAccount.setType(account.getType());
        newAccount.setBalance(account.getBalance());
        return accountRepository.save(newAccount);
    }

    @Override
    public Account addBalance(Long id, int amount, Long ownerId) {
        Account newAccount = accountRepository.findById(id).orElseThrow(() -> new AccountNotfoundException(id));
        Customer owner = null;// Will be gotten from user service
        int newBalance = newAccount.getBalance() + amount;
        newAccount.setBalance(newBalance);
        return accountRepository.save(newAccount);
    }

    @Override
    public Account withdrawBalance(Long id, int amount, Long ownerId) {
        Account newAccount = accountRepository.findById(id).orElseThrow(() -> new AccountNotfoundException(id));
        Customer owner = null; // Will be gotten from user service
        int newBalance = newAccount.getBalance() - amount;
        newAccount.setBalance(newBalance);
        return accountRepository.save(newAccount);
    }

    @Override
    public void delete(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotfoundException(id));
        this.accountRepository.delete(account);
    }

    @Override
    public void deleteAccountsUsingOwnerId(Long ownerId) {
        List<Account> accounts = accountRepository.findByOwnerId(ownerId);
        for (Account account : accounts) {
            this.accountRepository.delete(account);
        }
    }

    @Override
    public boolean withdrawBalanceAllAcounts(Long id, int amount, Long ownerId) {
        Account newAccount = accountRepository.findById(id).orElseThrow(() -> new AccountNotfoundException(id));
        List<Account> accounts = accountRepository.findByOwnerId(ownerId);
        Integer allAmount = 0;
        for (Account account : accounts) {
            allAmount += account.getBalance();
        }
        if (allAmount >= amount){
            Customer owner = null; // Will be gotten from user service
            int currentBalance = newAccount.getBalance();
            int newBalance = newAccount.getBalance() - amount;
            if (newBalance <= 0) {
                Integer amountToTake = amount - currentBalance;
                newAccount.setBalance(0);
                accountRepository.save(newAccount);
                for (Account account : accounts) {
                    Integer currentAccountBalance = account.getBalance();
                    if(amountToTake > 0 && account.getId() != id) {
                        if (account.getBalance() < amountToTake) {
                            account.setBalance(0);
                            amountToTake -= currentAccountBalance;
                        } else {
                            account.setBalance(account.getBalance() - amountToTake);
                            amountToTake = 0;
                        }
                        accountRepository.save(account);
                    }
                }
                return true;
            }else {
                newAccount.setBalance(newBalance);
                accountRepository.save(newAccount);
                return true;
            }
        }else{
            return false;
        }
    }
}