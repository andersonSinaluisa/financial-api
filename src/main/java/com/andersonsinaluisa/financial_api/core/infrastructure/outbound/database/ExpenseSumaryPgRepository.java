package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseSumaryPgRepository  extends JpaRepository<ExpenseSummaryEntity,Long> {
}
