package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class TransactionCreateDto {

    public LocalDateTime transaction_date;

    public double amount;

    public String category;

    public Long source_account_id;

    public Long destination_account_id;

    public String description;

    public String currency;

    public String transaction_type;

}
