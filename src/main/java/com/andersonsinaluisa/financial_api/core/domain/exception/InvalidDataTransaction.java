package com.andersonsinaluisa.financial_api.core.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid Account Source or Account Destination")  // 404
public class InvalidDataTransaction extends RuntimeException{
    public InvalidDataTransaction(String ex){
        super(ex);
    }
}
