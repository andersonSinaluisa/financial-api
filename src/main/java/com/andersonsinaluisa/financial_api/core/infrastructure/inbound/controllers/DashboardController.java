package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.controllers;

import com.andersonsinaluisa.financial_api.core.application.calculate.BalanceCalculateUseCase;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TotalSumaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private final BalanceCalculateUseCase balanceCalculateUseCase;


    @GetMapping
    public ResponseEntity<TotalSumaryDto> get(){

        TotalSumaryDto d = balanceCalculateUseCase.calculateCurrentBalance();

       return ResponseEntity.ok(d);
    }
}
