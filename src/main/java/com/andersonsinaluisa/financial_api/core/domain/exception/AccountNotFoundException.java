package com.andersonsinaluisa.financial_api.core.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid Account Source")  // 404
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String ex){
        super(ex);
    }
}
