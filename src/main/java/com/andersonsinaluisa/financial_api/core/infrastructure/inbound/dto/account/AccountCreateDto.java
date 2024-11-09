package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.account;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class AccountCreateDto {

    public String account_name;

    public String account_type;

    public String account_number;

    public String initial_balance;

    public String status;

}
