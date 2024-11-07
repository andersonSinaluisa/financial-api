package com.andersonsinaluisa.financial_api.account.domain.repository;

import com.andersonsinaluisa.financial_api.account.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Optional<Account> create(Account data);
    Optional<Account> update(Account data);
    Optional<Account> getById(long id);
    List<Account> all();
    void deleteById(long ig);
}
