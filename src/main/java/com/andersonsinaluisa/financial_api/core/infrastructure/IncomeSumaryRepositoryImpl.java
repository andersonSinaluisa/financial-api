package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.IncomeSumaryPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.IncomeSumaryEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.IncomeSummaryMapper;
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
public class IncomeSumaryRepositoryImpl implements IncomeSumaryRepository {

    private final IncomeSumaryPgRepository incomeSumaryPgRepository;

    @Override
    public Mono<IncomeSumary> create(IncomeSumary data) {

        IncomeSumaryEntity entity = IncomeSummaryMapper.fromDomainToEntity(data);
        return incomeSumaryPgRepository.save(entity).map(IncomeSummaryMapper::fromEntityToDomain);
    }

    @Override
    public Mono<IncomeSumary> update(IncomeSumary data) {
        IncomeSumaryEntity entity = IncomeSummaryMapper.fromDomainToEntity(data);
        return incomeSumaryPgRepository.save(entity).map(IncomeSummaryMapper::fromEntityToDomain);
    }

    @Override
    public Mono<IncomeSumary> getById(long id) {
        return incomeSumaryPgRepository.findById(id).map(IncomeSummaryMapper::fromEntityToDomain);
    }

    @Override
    public Flux<IncomeSumary> all() {
        return  incomeSumaryPgRepository.findAll().map(IncomeSummaryMapper::fromEntityToDomain);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return incomeSumaryPgRepository.deleteById(id);
    }

    @Override
    public Flux<IncomeSumary> getByRangeDate(LocalDate start_date, LocalDate end_date) {
        return incomeSumaryPgRepository.findByRangeDate(start_date,end_date).map(IncomeSummaryMapper::fromEntityToDomain);
    }

    @Override
    public Mono<IncomeSumary> getLast() {
        return incomeSumaryPgRepository.getLast()
                .map(IncomeSummaryMapper::fromEntityToDomain);
    }
}
