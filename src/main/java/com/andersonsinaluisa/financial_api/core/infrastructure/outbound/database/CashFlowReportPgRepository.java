package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.CashFlowReportEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CashFlowReportPgRepository extends ReactiveCrudRepository<CashFlowReportEntity,Long> {
}
