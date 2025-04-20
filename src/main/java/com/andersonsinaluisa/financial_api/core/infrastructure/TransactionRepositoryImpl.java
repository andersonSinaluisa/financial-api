package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.TransactionPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.TransactionEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.AccountMapper;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    public final TransactionPgRepository transactionPgRepository;

    @Override
    public Mono<Transaction> create(Transaction data) {

        TransactionEntity entity = TransactionMapper.fromDtoToDomain(data);
        return transactionPgRepository.save(entity).map(TransactionMapper::fromDomainToDto);
    }

    @Override
    public Mono<Transaction> update(Transaction data) {
        TransactionEntity entity = TransactionMapper.fromDtoToDomain(data);
        return transactionPgRepository.save(entity).map(TransactionMapper::fromDomainToDto);

    }

    @Override
    public Mono<Transaction> getById(long id) {

        return transactionPgRepository.findById(id).map(TransactionMapper::fromDomainToDto);
    }

    @Override
    public Mono<Page<Transaction>>all(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        Mono<List<Transaction>> content = transactionPgRepository
                .findAllByLimitAndOffset(pageSize, offset)
                .map(TransactionMapper::fromDomainToDto)
                .collectList();

        Mono<Long> count = transactionPgRepository.count();

        return Mono.zip(content, count)
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));

    }

    @Override
    public Mono<Void> deleteById(long id) {

        return transactionPgRepository.findById(id).flatMap(e ->{
            e.setDeleted(true);
           return transactionPgRepository.save(e);
        }).then();
    }

    @Override
    public Flux<Transaction> getByMonthAndYear(int month, int year) {

        return transactionPgRepository
                .findByTransactionDateYearAndTransactionDateMonth(year,month)
                .map(TransactionMapper::fromDomainToDto);
    }

    @Override
    public Flux<Transaction> getByAccountAndMonthAndYear(int month, int year,Long account_id) {

        return transactionPgRepository
                .findByYearMonthAndDestinationAccount(year,month,account_id)
                .map(TransactionMapper::fromDomainToDto);
    }

    @Override
    public Flux<Transaction> getByRange(LocalDate start, LocalDate end) {

        return transactionPgRepository.findByTransactionDateBetween(start,end)
                .map(TransactionMapper::fromDomainToDto);
    }

    @Override
    public Mono<Page<Transaction>> getByRange(LocalDate start, LocalDate end, Pageable pageable) {
        long limit = pageable.getPageSize();
        long offset = pageable.getOffset();

        Mono<List<Transaction>> content = transactionPgRepository
                .findByTransactionDateBetween(start, end, limit, offset)
                .map(TransactionMapper::fromDomainToDto)
                .collectList();

        Mono<Long> count = transactionPgRepository.countByTransactionDateBetween(start, end); // necesitas definir este método

        return Mono.zip(content, count)
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }
}
