package com.andersonsinaluisa.financial_api.core.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Budget {
    public Long id;

    public String department;

    public String project;

    public double assigned_budget;

    public double spent_amount;

    public double remaining_budget;

    public LocalDateTime created_at;
}
