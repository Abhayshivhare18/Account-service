package com.example.accountService.controller;

import com.example.accountService.model.Account;
import com.example.accountService.response.ResponseHandler;
import com.example.accountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountContoller {

    @Autowired
    public AccountService accountService;


    @PostMapping(value="/createAccount")
    public ResponseEntity<Object> createTransaction(@RequestBody Account  account){
        return ResponseHandler.reponseBuilder("save Account details",
                HttpStatus.OK, accountService.createAccount(account));
    }

    @GetMapping("/getAccount/{id}")
    public ResponseEntity<Object> getAccount(@PathVariable Long accountId){
        return ResponseHandler.reponseBuilder("get account details",
                HttpStatus.OK,accountService.getAccount(accountId));
    }

    @PutMapping("/updateAccount/balance")
    public ResponseEntity<Object> updateBalance(@RequestBody Account account) {
        return ResponseHandler.reponseBuilder("updated account details",
                HttpStatus.OK,accountService.updateBalance(account));

    }
}
