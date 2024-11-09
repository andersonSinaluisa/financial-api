package com.andersonsinaluisa.financial_api.core.application.create;


import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseSumaryCreateUseCase {

    @Autowired
    private ExpenseSumaryRepository expenseSumaryRepository;

    public ExpenseSummary create(ExpenseSummary data){
        return expenseSumaryRepository.create(data).orElseThrow();
    }



}
