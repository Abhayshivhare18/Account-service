package com.example.accountService.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {


     public static ResponseEntity<Object>reponseBuilder(String message,
                                                        HttpStatus httpStatus,
                                                        Object reponseObject){
         Map<String,Object> map = new HashMap<>();
         map.put("message",message);
         map.put("httpStatus",httpStatus);
         map.put("data",reponseObject);
         return  new ResponseEntity<>(map,httpStatus);
     }
}
