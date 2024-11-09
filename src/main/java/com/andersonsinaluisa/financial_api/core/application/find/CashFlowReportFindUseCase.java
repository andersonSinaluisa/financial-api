package com.andersonsinaluisa.financial_api.core.application.find;


import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.domain.repository.CashFlowReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashFlowReportFindUseCase {

    private final CashFlowReportRepository repository;

    public List<CashFlowReport> all(){
        return repository.all();
    }

    public CashFlowReport getBydId(long id){
        return repository.getById(id).orElseThrow();
    }

}
