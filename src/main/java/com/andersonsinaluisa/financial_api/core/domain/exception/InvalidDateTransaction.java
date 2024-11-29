package com.andersonsinaluisa.financial_api.core.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="INVALID_DATE_TRANSACTION")  // 404

public class InvalidDateTransaction extends RuntimeException{
    public InvalidDateTransaction(String ex){
        super(ex);
    }
}
