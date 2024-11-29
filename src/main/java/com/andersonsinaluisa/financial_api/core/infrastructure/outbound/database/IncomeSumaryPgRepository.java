package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.IncomeSumaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeSumaryPgRepository extends JpaRepository<IncomeSumaryEntity,Long> {
    @Query("SELECT e FROM IncomeSumaryEntity e WHERE e.start_date >= :start_date AND e.end_date <= :end_date")
    List<IncomeSumaryEntity> findByRangeDate(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date);
    @Query("SELECT i FROM IncomeSumaryEntity i ORDER BY i.created_at DESC limit 1")
    Optional<IncomeSumaryEntity> getLast();
}
