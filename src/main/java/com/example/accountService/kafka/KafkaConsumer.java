package com.example.accountService.kafka;

import com.example.accountService.exception.AccountServiceException;
import com.example.accountService.model.Account;
import com.example.accountService.model.RequestDTO.Transaction;
import com.example.accountService.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    AccountService accountService;

    private ObjectMapper objectMapper= new ObjectMapper();
    @KafkaListener(topics="TRANSACTION_TOPIC", groupId = "account-group")
    public void consume(String message){
        try{
            Transaction transaction = objectMapper.readValue(message,Transaction.class);
            Long accountId=transaction.getAccountId();
            BigDecimal amount =transaction.getAmount();

            Account account = accountService.getAccount(accountId);
            //Adjust balance based on transaction type
            if ("CREDIT".equalsIgnoreCase(transaction.getType())) {
                account.setBalance(account.getBalance().add(amount));
            } else if ("DEBIT".equalsIgnoreCase(transaction.getType())) {
                account.setBalance(account.getBalance().subtract(amount));
            }
            accountService.updateBalance(account);
        }catch (Exception e){
            log.error("Unexpected error while updating Account form consumer: {}", e.getMessage());
            throw new AccountServiceException("Unexpected error " +
                    "while updating Account",e);
        }

    }
}
