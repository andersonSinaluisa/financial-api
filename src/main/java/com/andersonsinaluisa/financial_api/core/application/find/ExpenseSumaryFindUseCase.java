package com.andersonsinaluisa.financial_api.core.application.find;

import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseSumaryFindUseCase {

    @Autowired
    private final ExpenseSumaryRepository expenseSumaryRepository;
    public Flux<ExpenseSummary> all(){
        return expenseSumaryRepository.all();
    }

    public Mono<ExpenseSummary> getById(long id){

        return expenseSumaryRepository.getById(id);
    }

    public Mono<ExpenseSummary> getCurrent(){
        return expenseSumaryRepository.getLast();
    }


}
