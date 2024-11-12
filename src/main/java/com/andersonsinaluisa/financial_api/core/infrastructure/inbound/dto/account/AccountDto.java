package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.account;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class AccountDto {
    public Long id;

    public String account_name;

    public String account_type;

    public String account_number;

    public double initial_balance;

    public String status;

    public LocalDateTime created_at;

    public LocalDateTime updated_at;
}
