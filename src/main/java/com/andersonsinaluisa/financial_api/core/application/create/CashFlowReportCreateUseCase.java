package com.andersonsinaluisa.financial_api.core.application.create;

import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.domain.repository.CashFlowReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CashFlowReportCreateUseCase {
    @Autowired
    private final CashFlowReportRepository repository;


    public Mono<CashFlowReport> create(CashFlowReport data){
        return  repository.create(data);
    }
}
