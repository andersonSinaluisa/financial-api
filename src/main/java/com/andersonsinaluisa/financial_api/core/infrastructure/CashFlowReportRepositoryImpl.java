package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.domain.repository.CashFlowReportRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.CashFlowReportPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.CashFlowReportEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.CashFlowReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class CashFlowReportRepositoryImpl implements CashFlowReportRepository {

    private final CashFlowReportPgRepository repository;

    @Override
    public Mono<CashFlowReport> create(CashFlowReport data) {
        CashFlowReportEntity cashFlowReportEntity = CashFlowReportMapper.fromDomainToEntity(data);
        return  repository.save(cashFlowReportEntity).map(
                CashFlowReportMapper::fromEntityToDomain
        );
    }

    @Override
    public Mono<CashFlowReport> update(CashFlowReport data) {
        CashFlowReportEntity c = CashFlowReportMapper.fromDomainToEntity(data);

        return repository.save(c).map(CashFlowReportMapper::fromEntityToDomain);
    }

    @Override
    public Mono<CashFlowReport> getById(long id)
    {
        return  repository.findById(id).map(CashFlowReportMapper::fromEntityToDomain);
    }

    @Override
    public Flux<CashFlowReport> all() {


        return repository.findAll().map(CashFlowReportMapper::fromEntityToDomain);

    }

    @Override
    public Mono<Void> deleteById(long id) {
        return repository.deleteById(id);
    }
}
