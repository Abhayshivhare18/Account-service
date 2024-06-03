package com.example.accountService.exception;

public class AccountServiceException extends  RuntimeException{

    public AccountServiceException(String message){
        super(message);
    }
    public AccountServiceException(String message,Throwable throwable){
        super(message,throwable);
    }
}
