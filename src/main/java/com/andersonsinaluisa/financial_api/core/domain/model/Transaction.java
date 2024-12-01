package com.andersonsinaluisa.financial_api.core.domain.model;

import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Builder
public class Transaction {
    public Long id;

    public LocalDateTime transaction_date;

    public double amount;

    public String category;

    public Long source_account;

    public Long destination_account;

    public String description;

    public String currency;

    public String transaction_type;

    public LocalDateTime created_at;

    public boolean deleted;

    public UUID identifier;
}
