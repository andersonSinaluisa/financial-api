package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.AccountEntity;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TransactionPgRepository extends ReactiveCrudRepository<TransactionEntity, Long> {

    // ✔️ Transacciones por año y mes (PostgreSQL usa EXTRACT)
    @Query("SELECT * FROM transactions WHERE EXTRACT(YEAR FROM transaction_date) = :year AND EXTRACT(MONTH FROM transaction_date) = :month")
    Flux<TransactionEntity> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    // ✔️ Por año, mes y cuenta de origen
    @Query("SELECT * FROM transactions WHERE EXTRACT(YEAR FROM transaction_date) = :year AND EXTRACT(MONTH FROM transaction_date) = :month AND source_account_id = :accountId")
    Flux<TransactionEntity> findByYearMonthAndSourceAccount(@Param("year") int year, @Param("month") int month, @Param("accountId") Long accountId);

    // ✔️ Por año, mes y cuenta de destino
    @Query("SELECT * FROM transactions WHERE EXTRACT(YEAR FROM transaction_date) = :year AND EXTRACT(MONTH FROM transaction_date) = :month AND destination_account_id = :accountId")
    Flux<TransactionEntity> findByYearMonthAndDestinationAccount(@Param("year") int year, @Param("month") int month, @Param("accountId") Long accountId);

    // ✔️ Paginado por limit/offset
    @Query("SELECT * FROM transactions LIMIT :limit OFFSET :offset")
    Flux<TransactionEntity> findAllByLimitAndOffset(@Param("limit") long limit, @Param("offset") long offset);

    // ✔️ Rango de fechas (ya corregido)
    @Query("SELECT * FROM transactions WHERE transaction_date BETWEEN :startDate AND :endDate ORDER BY transaction_date DESC LIMIT :limit OFFSET :offset")
    Flux<TransactionEntity> findByTransactionDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("limit") long limit,
            @Param("offset") long offset
    );

    @Query("SELECT * FROM transactions WHERE transaction_date BETWEEN :startDate AND :endDate ORDER BY transaction_date DESC")
    Flux<TransactionEntity> findByTransactionDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    @Query("SELECT * FROM transactions WHERE EXTRACT(YEAR FROM transaction_date) = :year AND EXTRACT(MONTH FROM transaction_date) = :month")
    Flux<TransactionEntity> findByTransactionDateYearAndTransactionDateMonth(int month, int year);

    // ✔️ Contar registros entre fechas
    @Query("SELECT COUNT(*) FROM transactions WHERE transaction_date BETWEEN :startDate AND :endDate")
    Mono<Long> countByTransactionDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
