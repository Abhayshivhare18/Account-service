package com.example.accountService.service;

import com.example.accountService.model.Account;

import java.math.BigDecimal;

public interface AccountService {

    public Account getAccount(Long accountId);

    public Account updateBalance(Account account);

    public Account createAccount(Account account);
}
