package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.BudgetEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BudgetPgRepository extends ReactiveCrudRepository<BudgetEntity,Long> {
}
