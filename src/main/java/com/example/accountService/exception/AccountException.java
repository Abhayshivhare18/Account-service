package com.example.accountService.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class AccountException {

    private final String message;

    private final  Throwable  throwable;

    private  final HttpStatus httpStatus;
}
