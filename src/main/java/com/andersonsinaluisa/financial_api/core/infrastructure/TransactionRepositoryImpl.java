package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.TransactionPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.TransactionEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.AccountMapper;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
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
    public Page<Transaction> all(Pageable pageable) {
        Page<TransactionEntity> stream = transactionPgRepository.findAll(pageable);
        return stream.map(TransactionMapper::fromDomainToDto);
    }

    @Override
    public void deleteById(long id) {
        Optional<TransactionEntity> e = transactionPgRepository.findById(id);
        if(e.isPresent()){
            TransactionEntity obj = e.get();
            obj.deleted = true;
            transactionPgRepository.save(obj);
            transactionPgRepository.deleteById(id);
        }

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

    @Override
    public List<Transaction> getByRange(LocalDate start, LocalDate end) {

        List<Transaction> list = transactionPgRepository.findByTransactionDateBetween(start,end)
                .stream()
                .map(TransactionMapper::fromDomainToDto)
                .toList();
        return list;
    }

    @Override
    public Page<Transaction> getByRange(LocalDate start, LocalDate end, Pageable pageable) {
        Page<TransactionEntity> page = transactionPgRepository.findByTransactionDateBetween(
                start, end, pageable
        );
        return page.map(TransactionMapper::fromDomainToDto);
    }
}
