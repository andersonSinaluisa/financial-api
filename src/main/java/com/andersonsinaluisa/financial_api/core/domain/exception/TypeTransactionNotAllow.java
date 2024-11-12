package com.andersonsinaluisa.financial_api.core.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Transaction not allow")  // 404
public class TypeTransactionNotAllow extends RuntimeException{
    public TypeTransactionNotAllow(String ex){
        super(ex);
    }
}
