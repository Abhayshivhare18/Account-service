package com.example.accountService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountExceptionContollerAdvice {


     @ExceptionHandler(value={AccountNotFoundException.class})
     public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException accountNotFoundException){
        AccountException accountException = new AccountException(
                accountNotFoundException.getMessage(),
                accountNotFoundException.getCause(),
                HttpStatus.NOT_FOUND);
          return new ResponseEntity<>(accountException,HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler(value={AccountServiceException.class})
     public ResponseEntity<Object>handleAccountServiceException(AccountServiceException accountServiceException){
       AccountException accountException = new AccountException(
               accountServiceException.getMessage(),
               accountServiceException.getCause(),
               HttpStatus.INTERNAL_SERVER_ERROR
       );
       return new ResponseEntity<>(accountException,HttpStatus.INTERNAL_SERVER_ERROR);

     }
}
