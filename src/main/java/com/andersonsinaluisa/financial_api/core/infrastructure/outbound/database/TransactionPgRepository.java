package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionPgRepository extends JpaRepository<TransactionEntity, Long> {

    // Buscar transacciones por año y mes usando JPQL
    @Query("SELECT t FROM TransactionEntity t WHERE YEAR(t.transaction_date) = :year AND MONTH(t.transaction_date) = :month")
    List<TransactionEntity> findByTransactionDateYearAndTransactionDateMonth(@Param("year") int year, @Param("month") int month);

    // Buscar transacciones por año, mes y cuenta de origen usando JPQL
    @Query("SELECT t FROM TransactionEntity t WHERE YEAR(t.transaction_date) = :year AND MONTH(t.transaction_date) = :month AND t.source_account.id = :accountId")
    List<TransactionEntity> findByTransactionDateYearAndTransactionDateMonthAndSourceAccount_Id(
            @Param("year") int year, @Param("month") int month, @Param("accountId") Long accountId);

    // Buscar transacciones por año, mes y cuenta de destino usando JPQL
    @Query("SELECT t FROM TransactionEntity t WHERE YEAR(t.transaction_date) = :year AND MONTH(t.transaction_date) = :month AND t.destination_account.id = :accountId")
    List<TransactionEntity> findByTransactionDateYearAndTransactionDateMonthAndDestinationAccount_Id(
            @Param("year") int year, @Param("month") int month, @Param("accountId") Long accountId);


    @Query("SELECT t FROM TransactionEntity t WHERE DATE(t.transaction_date) BETWEEN :startDate AND :endDate")
    List<TransactionEntity> findByTransactionDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT t FROM TransactionEntity t WHERE DATE(t.transaction_date) BETWEEN :startDate AND :endDate Order By t.transaction_date desc")
    Page<TransactionEntity> findByTransactionDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);
}