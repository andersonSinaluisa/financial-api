package com.andersonsinaluisa.financial_api.account.infrastructure.outbound.database;

import com.andersonsinaluisa.financial_api.account.infrastructure.outbound.database.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountPgRepository extends JpaRepository<AccountEntity, Long> {
}
