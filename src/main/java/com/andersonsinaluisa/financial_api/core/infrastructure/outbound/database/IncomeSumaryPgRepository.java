package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.IncomeSumaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeSumaryPgRepository extends JpaRepository<IncomeSumaryEntity,Long> {
}
