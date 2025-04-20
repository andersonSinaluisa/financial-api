package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.IncomeSumaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeSumaryPgRepository extends ReactiveCrudRepository<IncomeSumaryEntity, Long> {

    // ✅ SQL nativa con snake_case, como es típico en PostgreSQL
    @Query("SELECT * FROM income_sumary WHERE start_date >= :start_date AND end_date <= :end_date")
    Flux<IncomeSumaryEntity> findByRangeDate(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date);

    // ✅ Obtener el último registro por fecha
    @Query("SELECT * FROM income_sumary ORDER BY created_at DESC LIMIT 1")
    Mono<IncomeSumaryEntity> getLast();
}
