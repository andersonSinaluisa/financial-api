package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.AccountPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.AccountEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountPgRepository accountPgRepository;

    @Override
    public Mono<Optional<Account>> create(Account data) {

        AccountEntity accountEntity = AccountMapper.fromDtoToDomain(data);


        return this.accountPgRepository.save(accountEntity).map(
                saved -> Optional.of(AccountMapper.fromDomainToDto(saved))
        );
    }


    @Override
    public Mono<Optional<Account>> update(Account data) {
        AccountEntity accountEntity = AccountMapper.fromDtoToDomain(data);

        return this.accountPgRepository.save(accountEntity).map(
                saved -> Optional.of(AccountMapper.fromDomainToDto(saved))
        );
    }

    @Override
    public Mono<Optional<Account>> getById(long id) {

        return this.accountPgRepository.findById(id).map(
          saved ->  Optional.of(AccountMapper.fromDomainToDto(saved))
        );
    }

    public Mono<Page<Account>> all(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        Mono<List<Account>> content = accountPgRepository
                .findAllByLimitAndOffset(pageSize, offset)
                .map(AccountMapper::fromDomainToDto)
                .collectList();

        Mono<Long> count = accountPgRepository.count();

        return Mono.zip(content, count)
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }


    @Override
    public Mono<Page<Account>> all(String search,Pageable pageable){
        long limit = pageable.getPageSize();
        long offset = pageable.getOffset();

        Mono<List<Account>> content = accountPgRepository.findBySearchWithPagination(search, limit, offset)
                .map(AccountMapper::fromDomainToDto)
                .collectList();

        Mono<Long> count = accountPgRepository.countBySearch(search);

        return Mono.zip(content, count)
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));


    }

    @Override
    public Flux<Account> all() {
        return accountPgRepository.findAll().map(AccountMapper::fromDomainToDto);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return this.accountPgRepository.findById(id)
                .flatMap(account -> {
                    account.setDeleted(true);
                    return accountPgRepository.save(account);
                })
                .then();
    }

    @Override
    public Mono<Account> getBySlug(String slug) {
        return this.accountPgRepository.findBySlug(slug)
                .map(AccountMapper::fromDomainToDto);
    }


}
