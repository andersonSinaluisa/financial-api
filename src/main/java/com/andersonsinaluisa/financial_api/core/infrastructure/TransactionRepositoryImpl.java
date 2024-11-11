package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.TransactionPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.TransactionEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.AccountMapper;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    public final TransactionPgRepository transactionPgRepository;

    @Override
    public Optional<Transaction> create(Transaction data) {

        TransactionEntity entity = TransactionMapper.fromDtoToDomain(data);
        entity = transactionPgRepository.save(entity);
        return Optional.of(TransactionMapper.fromDomainToDto(entity));
    }

    @Override
    public Optional<Transaction> update(Transaction data) {
        TransactionEntity entity = TransactionMapper.fromDtoToDomain(data);
        entity = transactionPgRepository.save(entity);
        return Optional.of(TransactionMapper.fromDomainToDto(entity));

    }

    @Override
    public Optional<Transaction> getById(long id) {

        Optional<TransactionEntity> t = transactionPgRepository.findById(id);
        return t.map(TransactionMapper::fromDomainToDto);
    }

    @Override
    public List<Transaction> all() {
        Stream<Transaction> stream = transactionPgRepository.findAll().stream().map(TransactionMapper::fromDomainToDto);
        return stream.toList();
    }

    @Override
    public void deleteById(long id) {
        transactionPgRepository.deleteById(id);
    }

    @Override
    public List<Transaction> getByMonthAndYear(int month, int year) {

        List<Transaction> transactions = transactionPgRepository
                .findByTransactionDateYearAndTransactionDateMonth(year,month)
                .stream()
                .map(TransactionMapper::fromDomainToDto).toList();
        return transactions;
    }

    @Override
    public List<Transaction> getByAccountAndMonthAndYear(int month, int year,Long account_id) {

        List<Transaction> transactions = transactionPgRepository
                .findByTransactionDateYearAndTransactionDateMonthAndSourceAccount_Id(year,month,account_id)
                .stream()
                .map(TransactionMapper::fromDomainToDto).toList();
        return List.of();
    }
}
