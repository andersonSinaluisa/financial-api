package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseSumaryPgRepository extends ReactiveCrudRepository<ExpenseSummaryEntity, Long> {

    // Consulta con SQL nativo para el rango de fechas
    @Query("SELECT * FROM expense_summary WHERE start_date >= :start_date AND end_date <= :end_date")
    Flux<ExpenseSummaryEntity> findByRangeDate(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date);

    // Obtener el último por fecha de creación
    @Query("SELECT * FROM expense_summary ORDER BY created_at DESC LIMIT 1")
    Mono<ExpenseSummaryEntity> getLast();
}
