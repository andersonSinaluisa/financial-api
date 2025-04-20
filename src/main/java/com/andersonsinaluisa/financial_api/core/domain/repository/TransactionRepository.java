package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Mono<Transaction> create(Transaction data);
    Mono<Transaction> update(Transaction data);
    Mono<Transaction> getById(long id);
    Mono<Page<Transaction>> all(Pageable pageable);
    Mono<Void> deleteById(long ig);

    Flux<Transaction> getByMonthAndYear(int month, int year);

    Flux<Transaction> getByAccountAndMonthAndYear(int month, int year, Long account_id);

    Flux<Transaction> getByRange(LocalDate start, LocalDate end);


    Mono<Page<Transaction>> getByRange(LocalDate start, LocalDate end, Pageable pageable);

}
