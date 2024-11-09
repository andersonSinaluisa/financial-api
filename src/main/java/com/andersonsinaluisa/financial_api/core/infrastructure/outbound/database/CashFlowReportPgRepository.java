package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.CashFlowReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashFlowReportPgRepository extends JpaRepository<CashFlowReportEntity,Long> {
}
