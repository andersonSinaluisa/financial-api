package com.andersonsinaluisa.financial_api.core.domain.repository;
import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CashFlowReportRepository {
    Mono<CashFlowReport> create(CashFlowReport data);
    Mono<CashFlowReport> update(CashFlowReport data);
    Mono<CashFlowReport> getById(long id);
    Flux<CashFlowReport> all();
    Mono<Void> deleteById(long ig);
}
