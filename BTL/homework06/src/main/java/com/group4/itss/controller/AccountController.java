package com.group4.itss.controller;

import com.group4.itss.entity.model.Account;
import com.group4.itss.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("account-controller")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    public boolean isExistedEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    public Account findByEmail(String email) {
        Optional<Account> account = accountRepository.findByEmail(email);
        return account.orElse(null);
    }
}
