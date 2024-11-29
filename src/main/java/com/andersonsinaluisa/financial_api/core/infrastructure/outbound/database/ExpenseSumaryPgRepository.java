package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseSumaryPgRepository  extends JpaRepository<ExpenseSummaryEntity,Long> {

    @Query("SELECT e FROM ExpenseSummaryEntity e WHERE e.start_date >= :start_date AND e.end_date <= :end_date")
    List<ExpenseSummaryEntity> findByRangeDate(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date);
    @Query("SELECT i FROM ExpenseSummaryEntity i ORDER BY i.created_at DESC limit 1")
    Optional<ExpenseSummaryEntity> getLast();
}
