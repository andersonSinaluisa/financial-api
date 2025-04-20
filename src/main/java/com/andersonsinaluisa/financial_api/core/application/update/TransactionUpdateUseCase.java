package com.andersonsinaluisa.financial_api.core.application.update;


import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionUpdateUseCase {

    private final TransactionRepository repository;

    public Mono<Transaction> update(Transaction data){
        return repository.update(data);
    }

}
