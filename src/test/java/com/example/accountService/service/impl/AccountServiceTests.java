package com.example.accountService.service.impl;

import com.example.accountService.model.Account;
import com.example.accountService.repository.AccountRepository;
import com.example.accountService.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class AccountServiceTests {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void testGetAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(1000));

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccount(1L);

        assertNotNull(foundAccount);
        assertEquals(1L, foundAccount.getId());
        assertEquals(BigDecimal.valueOf(1000), foundAccount.getBalance());
    }

    @Test
    public void testUpdateBalance() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.valueOf(1000));

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        BigDecimal newBalance = BigDecimal.valueOf(1500);
        account.setBalance(newBalance);
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        Account updatedAccount = accountService.updateBalance(account);

        assertNotNull(updatedAccount);
        assertEquals(1L, updatedAccount.getId());
        assertEquals(newBalance, updatedAccount.getBalance());
    }

}
