package com.andersonsinaluisa.financial_api.account.infrastructure.inbound.controllers;
import com.andersonsinaluisa.financial_api.account.application.find.AccountFindUseCase;
import com.andersonsinaluisa.financial_api.account.domain.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountFindUseCase accountFindUseCase =null;

    @GetMapping
    public ResponseEntity<List<Account>> all(){
        return ResponseEntity.ok(accountFindUseCase.findAll());
    }
}
