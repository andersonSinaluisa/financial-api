package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetPgRepository extends JpaRepository<Long, BudgetEntity> {
}
