package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseSumaryRepository {
    Mono<ExpenseSummary> create(ExpenseSummary data);
    Mono<ExpenseSummary> update(ExpenseSummary data);
    Mono<ExpenseSummary> getById(long id);
    Flux<ExpenseSummary> all();
    void deleteById(long ig);

    Flux<ExpenseSummary> getByRangeDate(LocalDate start_date, LocalDate end_date);
    Mono<ExpenseSummary> getLast();

}