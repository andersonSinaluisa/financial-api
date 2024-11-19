package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Optional<Account> create(Account data);
    Optional<Account> update(Account data);
    Optional<Account> getById(long id);
    Page<Account> all(Pageable pageable);
    List<Account> all();
    void deleteById(long ig);
}
