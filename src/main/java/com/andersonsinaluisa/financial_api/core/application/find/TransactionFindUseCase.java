package com.andersonsinaluisa.financial_api.core.application.find;


import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionFindUseCase {
    @Autowired
    private final TransactionRepository repository;

    public Transaction getById(long id){
        return  repository.getById(id).orElseThrow();
    }

    public List<Transaction> all(){
        return repository.all();
    }

    public List<Transaction> getByMonthAndYear(int month,int year){
        return repository.getByMonthAndYear(month,year);
    }

    public List<Transaction> getByAccountAndMonthAndYear(int month, int year, Long account_id){
        return repository.getByAccountAndMonthAndYear(month,year,account_id);
    }
}
