package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.IncomeSumaryPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.IncomeSumaryEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.IncomeSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IncomeSumaryRepositoryImpl implements IncomeSumaryRepository {

    @Autowired
    private final IncomeSumaryPgRepository incomeSumaryPgRepository;

    @Override
    public Optional<IncomeSumary> create(IncomeSumary data) {

        IncomeSumaryEntity entity = IncomeSummaryMapper.fromDomainToEntity(data);
        entity = incomeSumaryPgRepository.save(entity);
        return Optional.of(IncomeSummaryMapper.fromEntityToDomain(entity));
    }

    @Override
    public Optional<IncomeSumary> update(IncomeSumary data) {
        IncomeSumaryEntity entity = IncomeSummaryMapper.fromDomainToEntity(data);
        entity = incomeSumaryPgRepository.save(entity);
        return Optional.of(IncomeSummaryMapper.fromEntityToDomain(entity));
    }

    @Override
    public Optional<IncomeSumary> getById(long id) {
        return incomeSumaryPgRepository.findById(id).map(IncomeSummaryMapper::fromEntityToDomain);
    }

    @Override
    public List<IncomeSumary> all() {
        List<IncomeSumary> list = incomeSumaryPgRepository.findAll().stream().map(IncomeSummaryMapper::fromEntityToDomain).toList();
        return list;
    }

    @Override
    public void deleteById(long id) {
        incomeSumaryPgRepository.deleteById(id);
    }

    @Override
    public List<IncomeSumary> getByRangeDate(LocalDate start_date, LocalDate end_date) {
        return List.of();
    }

    @Override
    public Optional<IncomeSumary> getLast() {
        return incomeSumaryPgRepository.getLast()
                .map(IncomeSummaryMapper::fromEntityToDomain);
    }
}
