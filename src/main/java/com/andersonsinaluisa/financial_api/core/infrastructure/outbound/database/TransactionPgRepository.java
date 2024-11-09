package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionPgRepository extends JpaRepository<TransactionEntity, Long> {
}
