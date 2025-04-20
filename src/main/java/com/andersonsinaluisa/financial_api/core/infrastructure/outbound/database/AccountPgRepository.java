package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface AccountPgRepository extends ReactiveCrudRepository<AccountEntity, Long> {

    // Búsqueda por nombre o número de cuenta con paginación manual
    @Query("SELECT * FROM account WHERE LOWER(account_name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(account_number) LIKE LOWER(CONCAT('%', :search, '%')) ORDER BY id DESC LIMIT :limit OFFSET :offset")
    Flux<AccountEntity> findBySearchWithPagination(@Param("search") String search, @Param("limit") long limit, @Param("offset") long offset);

    // Contador para paginación
    @Query("SELECT COUNT(*) FROM account WHERE LOWER(account_name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(account_number) LIKE LOWER(CONCAT('%', :search, '%'))")
    Mono<Long> countBySearch(@Param("search") String search);

    @Query("SELECT * FROM account LIMIT :limit OFFSET :offset")
    Flux<AccountEntity> findAllByLimitAndOffset(long limit, long offset);

    Mono<AccountEntity> findBySlug(String slug);
}
