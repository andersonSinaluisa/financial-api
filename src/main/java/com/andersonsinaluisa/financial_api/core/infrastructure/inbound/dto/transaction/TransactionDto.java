package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class TransactionDto {
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
}
