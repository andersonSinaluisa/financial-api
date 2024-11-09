package com.andersonsinaluisa.financial_api.core.domain.model;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Account {
    public Long id;

    public String account_name;

    public String account_type;

    public String account_number;

    public String initial_balance;

    public String status;

    public LocalDateTime created_at;

    public LocalDateTime updated_at;

}
