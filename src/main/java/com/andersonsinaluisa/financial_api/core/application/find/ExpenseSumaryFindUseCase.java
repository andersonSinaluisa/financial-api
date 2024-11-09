package com.andersonsinaluisa.financial_api.core.application.find;

import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseSumaryFindUseCase {

    @Autowired
    private final ExpenseSumaryRepository expenseSumaryRepository;

    public List<ExpenseSummary> all(){
        return expenseSumaryRepository.all();
    }

    public ExpenseSummary getById(long id){
        return expenseSumaryRepository.getById(id).orElseThrow();
    }
}
