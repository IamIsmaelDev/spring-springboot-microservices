package com.reto2.dto;

import com.reto2.model.Account;
import com.reto2.model.Customer;
import com.reto2.dto.AccountDTO;
import com.reto2.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public Account convertDTOtoEntity(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null; // o lanzar una excepción si lo prefieres
        }

        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setType(accountDTO.getType());
        account.setOpeningDate(accountDTO.getOpeningDate());
        account.setBalance(accountDTO.getBalance());
        account.setOwnerId(accountDTO.getOwnerId());

        return account;
    }

    public AccountDTO convertEntityToDTO(Account account) {
        if (account == null) {
            return null; // o lanzar una excepción si lo prefieres
        }

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setType(account.getType());
        accountDTO.setOpeningDate(account.getOpeningDate());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setOwnerId(account.getOwnerId());

        return accountDTO;
    }

    public Customer converterDTOtoEntity(CustomerDTO customerDTO){

        if (customerDTO == null) {
            return null; // o lanzar una excepción si lo prefieres
        }

        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        return customer;

    }

    public CustomerDTO converterEntitytoDTO(Customer customer){

        if (customer == null) {
            return null; // o lanzar una excepción si lo prefieres
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());

        return customerDTO;

    }

}
