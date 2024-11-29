package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeSumaryRepository {
    Optional<IncomeSumary> create(IncomeSumary data);
    Optional<IncomeSumary> update(IncomeSumary data);
    Optional<IncomeSumary> getById(long id);
    List<IncomeSumary> all();
    void deleteById(long ig);
    List<IncomeSumary> getByRangeDate(LocalDate start_date, LocalDate end_date);


    Optional<IncomeSumary> getLast();
}
