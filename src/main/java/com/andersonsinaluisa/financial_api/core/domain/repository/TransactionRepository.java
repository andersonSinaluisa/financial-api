package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> create(Transaction data);
    Optional<Transaction> update(Transaction data);
    Optional<Transaction> getById(long id);
    Page<Transaction> all(Pageable pageable);
    void deleteById(long ig);

    List<Transaction> getByMonthAndYear(int month, int year);

    List<Transaction> getByAccountAndMonthAndYear(int month, int year, Long account_id);

    List<Transaction> getByRange(LocalDate start,LocalDate end);


    Page<Transaction> getByRange(LocalDate start, LocalDate end, Pageable pageable);

}
