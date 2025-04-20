package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.ExpenseSumaryPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.ExpenseSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExpenseSumaryRepositoryImpl implements ExpenseSumaryRepository {

    private final ExpenseSumaryPgRepository expenseSumaryPgRepository;

    @Override
    public Mono<ExpenseSummary> create(ExpenseSummary data) {
        ExpenseSummaryEntity e = ExpenseSummaryMapper.fromDomainToEntity(data);
        return expenseSumaryPgRepository.save(e).map(ExpenseSummaryMapper::fromEntityToDomain);
    }

    @Override
    public Mono<ExpenseSummary> update(ExpenseSummary data) {
        ExpenseSummaryEntity e = ExpenseSummaryMapper.fromDomainToEntity(data);
        return  expenseSumaryPgRepository.save(e).map(ExpenseSummaryMapper::fromEntityToDomain);

    }

    @Override
    public Mono<ExpenseSummary> getById(long id) {

        return expenseSumaryPgRepository.findById(id).map(ExpenseSummaryMapper::fromEntityToDomain);
    }

    @Override
    public Flux<ExpenseSummary> all() {
        return expenseSumaryPgRepository.findAll().map(ExpenseSummaryMapper::fromEntityToDomain);
    }

    @Override
    public void deleteById(long id) {
        expenseSumaryPgRepository.deleteById(id);
    }

    @Override
    public Flux<ExpenseSummary> getByRangeDate(LocalDate start_date, LocalDate end_date) {



        return expenseSumaryPgRepository
                .findByRangeDate(start_date,end_date)
                .map(ExpenseSummaryMapper::fromEntityToDomain);
    }

    @Override
    public Mono<ExpenseSummary> getLast() {
        return  expenseSumaryPgRepository.getLast()
                .map(ExpenseSummaryMapper::fromEntityToDomain);

    }
}
