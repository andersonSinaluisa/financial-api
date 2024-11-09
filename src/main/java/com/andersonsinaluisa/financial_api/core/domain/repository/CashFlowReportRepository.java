package com.andersonsinaluisa.financial_api.core.domain.repository;
import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;

import java.util.List;
import java.util.Optional;

public interface CashFlowReportRepository {
    Optional<CashFlowReport> create(CashFlowReport data);
    Optional<CashFlowReport> update(CashFlowReport data);
    Optional<CashFlowReport> getById(long id);
    List<CashFlowReport> all();
    void deleteById(long ig);
}
