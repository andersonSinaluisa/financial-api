package com.andersonsinaluisa.financial_api.account.infrastructure;

import com.andersonsinaluisa.financial_api.account.domain.model.Account;
import com.andersonsinaluisa.financial_api.account.domain.repository.AccountRepository;
import com.andersonsinaluisa.financial_api.account.infrastructure.outbound.database.AccountPgRepository;
import com.andersonsinaluisa.financial_api.account.infrastructure.outbound.database.entities.AccountEntity;
import com.andersonsinaluisa.financial_api.account.infrastructure.outbound.database.mappers.AccountMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountPgRepository accountPgRepository;

    public AccountRepositoryImpl(AccountPgRepository re){
        this.accountPgRepository = re;
    }

    @Override
    public Optional<Account> create(Account data) {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.account_name = data.account_name;
        accountEntity.account_number = data.account_number;
        accountEntity.account_type = data.account_type;
        accountEntity.created_at = data.created_at;
        accountEntity.status = data.status;
        accountEntity.initial_balance = data.initial_balance;
        accountEntity.updated_at = data.updated_at;
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
