package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> create(Transaction data);
    Optional<Transaction> update(Transaction data);
    Optional<Transaction> getById(long id);
    List<Transaction> all();
    void deleteById(long ig);

    List<Transaction> getByMonthAndYear(int month, int year);

    List<Transaction> getByAccountAndMonthAndYear(int month, int year, Long account_id);



}
