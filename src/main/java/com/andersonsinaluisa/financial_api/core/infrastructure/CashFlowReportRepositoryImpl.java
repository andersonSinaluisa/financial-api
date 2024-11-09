package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.domain.repository.CashFlowReportRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.CashFlowReportPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.CashFlowReportEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.CashFlowReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class CashFlowReportRepositoryImpl implements CashFlowReportRepository {

    @Autowired
    private final CashFlowReportPgRepository repository;

    @Override
    public Optional<CashFlowReport> create(CashFlowReport data) {
        CashFlowReportEntity cashFlowReportEntity = CashFlowReportMapper.fromDomainToEntity(data);
        cashFlowReportEntity = repository.save(cashFlowReportEntity);
        return Optional.of(CashFlowReportMapper.fromEntityToDomain(cashFlowReportEntity));
    }

    @Override
    public Optional<CashFlowReport> update(CashFlowReport data) {
        CashFlowReportEntity c = CashFlowReportMapper.fromDomainToEntity(data);
        c = repository.save(c);
        return Optional.of(CashFlowReportMapper.fromEntityToDomain(c));
    }

    @Override
    public Optional<CashFlowReport> getById(long id)
    {

        Optional<CashFlowReportEntity> c = repository.findById(id);
        return c.map(CashFlowReportMapper::fromEntityToDomain);
    }

    @Override
    public List<CashFlowReport> all() {

        Stream<CashFlowReport> stream = repository.findAll().stream().map(CashFlowReportMapper::fromEntityToDomain);

        return stream.toList();

    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
