package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.ExpenseSumaryPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.ExpenseSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExpenseSumaryRepositoryImpl implements ExpenseSumaryRepository {

    @Autowired
    private final ExpenseSumaryPgRepository expenseSumaryPgRepository;

    @Override
    public Optional<ExpenseSummary> create(ExpenseSummary data) {
        ExpenseSummaryEntity e = ExpenseSummaryMapper.fromDomainToEntity(data);
        e = expenseSumaryPgRepository.save(e);
        return Optional.of(ExpenseSummaryMapper.fromEntityToDomain(e));
    }

    @Override
    public Optional<ExpenseSummary> update(ExpenseSummary data) {
        ExpenseSummaryEntity e = ExpenseSummaryMapper.fromDomainToEntity(data);
        e = expenseSumaryPgRepository.save(e);
        return Optional.of(ExpenseSummaryMapper.fromEntityToDomain(e));
    }

    @Override
    public Optional<ExpenseSummary> getById(long id) {
        Optional<ExpenseSummaryEntity> e = expenseSumaryPgRepository.findById(id);
        return Optional.of(e.map(ExpenseSummaryMapper::fromEntityToDomain).get());
    }

    @Override
    public List<ExpenseSummary> all() {
        List<ExpenseSummary> list = expenseSumaryPgRepository.findAll().stream().map(ExpenseSummaryMapper::fromEntityToDomain).toList();
        return list;
    }

    @Override
    public void deleteById(long id) {
        expenseSumaryPgRepository.deleteById(id);
    }

    @Override
    public List<ExpenseSummary> getByRangeDate(LocalDate start_date, LocalDate end_date) {
        List<ExpenseSummary> entityList = expenseSumaryPgRepository
                                                .findByRangeDate(start_date,end_date)
                                                .stream().map(ExpenseSummaryMapper::fromEntityToDomain)
                                                .toList();


        return entityList;
    }

    @Override
    public Optional<ExpenseSummary> getLast() {
        return  expenseSumaryPgRepository.getLast()
                .map(ExpenseSummaryMapper::fromEntityToDomain);

    }
}
