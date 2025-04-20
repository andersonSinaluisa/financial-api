package com.andersonsinaluisa.financial_api.core.application.find;


import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionFindUseCase {
    @Autowired
    private final TransactionRepository repository;

    public Mono<Transaction> getById(long id){
        return  repository.getById(id);
    }

    public Mono<Page<Transaction>> all(Pageable pageable, LocalDate start, LocalDate end){

        Calendar calendar = Calendar.getInstance();

        int last_day_of_month = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        if(start==null){
            start = LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),1);
        }
        if(end==null){
            end = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), last_day_of_month);
        }
        return repository.getByRange(start,end,pageable);
    }

    public Flux<Transaction> getByMonthAndYear(int month, int year){
        return repository.getByMonthAndYear(month,year);
    }

    public Flux<Transaction> getByAccountAndMonthAndYear(int month, int year, Long account_id){
        return repository.getByAccountAndMonthAndYear(month,year,account_id);
    }

    public  Flux<Transaction> getByRange(LocalDate start, LocalDate end){
        return repository.getByRange(start,end);
    }
}
