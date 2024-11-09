package com.andersonsinaluisa.financial_api.core.infrastructure;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.AccountPgRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.AccountEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private final AccountPgRepository accountPgRepository;


    @Override
    public Optional<Account> create(Account data) {

        AccountEntity accountEntity = AccountMapper.fromDtoToDomain(data);

        accountEntity = this.accountPgRepository.save(accountEntity);
        return Optional.of(AccountMapper.fromDomainToDto(accountEntity));
    }

    @Override
    public Optional<Account> update(Account data) {
        AccountEntity accountEntity = AccountMapper.fromDtoToDomain(data);
        accountEntity = this.accountPgRepository.save(accountEntity);
        return Optional.of(AccountMapper.fromDomainToDto(accountEntity));
    }

    @Override
    public Optional<Account> getById(long id) {
        Optional<AccountEntity> accountEntity = this.accountPgRepository.findById(id);
        if(accountEntity.isPresent()){
            AccountEntity accountEntity1 = accountEntity.get();
            return Optional.of(AccountMapper.fromDomainToDto(accountEntity1));

        }
        return Optional.empty();
    }

    @Override
    public List<Account> all() {
        Stream<Account> objectStream = accountPgRepository.findAll().stream().map(AccountMapper::fromDomainToDto);
        return objectStream.toList();
    }

    @Override
    public void deleteById(long id) {

        Optional<AccountEntity> account = this.accountPgRepository.findById(id);
        if(account.isPresent()){
            AccountEntity data = account.get();
            data.deleted = true;
            accountPgRepository.save(data);
        }
    }
}
