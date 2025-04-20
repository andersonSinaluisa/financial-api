package com.andersonsinaluisa.financial_api.core.application.find;


import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.domain.repository.CashFlowReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashFlowReportFindUseCase {

    private final CashFlowReportRepository repository;

    public Flux<CashFlowReport> all(){
        return repository.all();
    }

    public Mono<CashFlowReport> getBydId(long id){
        return repository.getById(id);
    }

}
