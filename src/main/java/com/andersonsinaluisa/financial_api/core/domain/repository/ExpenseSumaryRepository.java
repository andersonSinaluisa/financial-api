package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseSumaryRepository {
    Optional<ExpenseSummary> create(ExpenseSummary data);
    Optional<ExpenseSummary> update(ExpenseSummary data);
    Optional<ExpenseSummary> getById(long id);
    List<ExpenseSummary> all();
    void deleteById(long ig);

    List<ExpenseSummary> getByRangeDate(LocalDate start_date, LocalDate end_date);
}