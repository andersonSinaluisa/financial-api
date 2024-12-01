package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BudgetRepository {

    Optional<Budget> create(Budget data);

    Optional<Budget> getById(Long id);


    Page<Budget> fromPaginable(Pageable page);
}
