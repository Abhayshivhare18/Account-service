package com.example.accountService.model.RequestDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {
    private Long id;
    private BigDecimal amount;
    private String type;
    private long accountId;
}
