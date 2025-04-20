package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeSumaryRepository {
    Mono<IncomeSumary> create(IncomeSumary data);
    Mono<IncomeSumary> update(IncomeSumary data);
    Mono<IncomeSumary> getById(long id);
    Flux<IncomeSumary> all();
    Mono<Void> deleteById(long ig);
    Flux<IncomeSumary> getByRangeDate(LocalDate start_date, LocalDate end_date);


    Mono<IncomeSumary> getLast();
}
