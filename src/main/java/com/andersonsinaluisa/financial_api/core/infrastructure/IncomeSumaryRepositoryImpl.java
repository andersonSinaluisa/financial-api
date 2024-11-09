package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.IncomeSumaryPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.IncomeSumaryEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.IncomeSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Optional<IncomeSumaryEntity> e = incomeSumaryPgRepository.findById(id);
        return Optional.of(e.map(IncomeSummaryMapper::fromEntityToDomain).get());
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
}
