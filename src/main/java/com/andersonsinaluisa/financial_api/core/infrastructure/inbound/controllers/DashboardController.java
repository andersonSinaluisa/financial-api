package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.controllers;

import com.andersonsinaluisa.financial_api.core.application.calculate.BalanceCalculateUseCase;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.dashboard.BalanceDto;
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
    public ResponseEntity<BalanceDto> get(
            @RequestParam("start")LocalDate start,
            @RequestParam("end") LocalDate end
    ){

       double d = balanceCalculateUseCase.calculateCurrentBalance(start,end);
       BalanceDto dto = BalanceDto.builder().currentBalace(d).build();

       return ResponseEntity.ok(dto);
    }
}
