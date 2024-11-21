package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountPgRepository extends JpaRepository<AccountEntity, Long> {


    @Query("SELECT a FROM AccountEntity a WHERE LOWER(a.account_name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(a.account_number) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<AccountEntity> findBySearch(@Param("search") String search, Pageable pageable);
}
