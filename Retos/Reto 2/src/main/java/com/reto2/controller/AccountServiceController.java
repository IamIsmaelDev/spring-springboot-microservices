package com.reto2.controller;

import com.reto2.exception.AccountNotfoundException;
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
        if(account.getOwnerId().equals(oid))
            return ResponseEntity.status(HttpStatus.OK.value()).body(account);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Error");
    }

    @Override
    public ResponseEntity createAccount(Account account, Long oid) {
        account.setOwnerId(oid);
        Account account1 = accountService.create(account);
        return ResponseEntity.status(HttpStatus.OK.value()).body(account);
    }

    @Override
    public ResponseEntity updateAccount(Account account, Long oid) {
        // Busca una cuenta en BD que corresponda con la que se quiere actualizar y la guarda
        Account newAccount = accountRepository.findById(account.getId()).orElseThrow(() -> new AccountNotfoundException(account.getId()));


        if(newAccount.getOwnerId().equals(oid)) {
            accountService.updateAccount(account.getId(), account);
            return ResponseEntity.status(HttpStatus.OK.value()).body(account);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("No existe la cuenta para este usuario");
        }
    }

    @Override
    public ResponseEntity deleteAccount(Long oid, Long aid) {
        // Cuando no se encuentre cuenta en BD dar excepción personalizada
        Account deleteAccount = accountRepository.findById(aid).orElseThrow(() -> new AccountNotfoundException(aid));

        if(deleteAccount.getOwnerId().equals(oid)) {
            accountService.delete(aid);
            return ResponseEntity.status(HttpStatus.OK.value()).body("borrado exitosamente");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("No se ha podido realizar la acción");
        }

    }

    @Override
    public ResponseEntity addMoneyToBalance(int balance, Long aid, Long oid) {
        Account newAccount = accountRepository.findById(aid).orElseThrow(() -> new AccountNotfoundException(aid));
        newAccount.setBalance(
                (newAccount.getBalance() + balance)
        );
        if(newAccount.getOwnerId().equals(oid)) {
            accountService.updateAccount(newAccount.getId(), newAccount);
            return ResponseEntity.status(HttpStatus.OK.value()).body("Se ha ingresado dinero");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("No existe la cuenta para este usuario");
        }
    }

    @Override
    public ResponseEntity takeMoneyToBalance(int balance, Long aid, Long oid) {
        Account newAccount = accountRepository.findById(aid).orElseThrow(() -> new AccountNotfoundException(aid));
        newAccount.setBalance(
                (newAccount.getBalance() - balance)
        );
        if(newAccount.getOwnerId().equals(oid)) {
            accountService.updateAccount(newAccount.getId(), newAccount);
            return ResponseEntity.status(HttpStatus.OK.value()).body("Se ha retirado dinero");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("No existe la cuenta para este usuario");
        }
    }

    @Override
    public ResponseEntity deleteAccountsByUser(Long oid) {
        accountService.deleteAccountsUsingOwnerId(oid);
        return ResponseEntity.status(HttpStatus.OK.value()).body("borrado exitosamente");
    }

    @Override
    public ResponseEntity loan(Long oid, Integer money) {
        List<Account> accountList = accountService.getAccountByOwnerId(oid);
        Integer balanceTotal = 0;
        for (int i = 0; i < accountList.size(); i++){
            balanceTotal += accountList.get(i).getBalance();
        }
        double balancePercentage = (balanceTotal * 0.8);
        if (balancePercentage >= money ){
            return ResponseEntity.status(HttpStatus.OK.value()).body("Puede realizar el préstamo");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Supera el 80% de la cantidad total de sus cuentas");
        }
    }

    @Override
    public ResponseEntity takeMoneyToBalanceAllAccounts(int balance, Long aid, Long oid) {
        if(accountService.withdrawBalanceAllAcounts(aid,balance,oid))
            return ResponseEntity.status(HttpStatus.OK.value()).body("Se ha retirado dinero");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("No se ha podido retirar el dinero");
    }

}
