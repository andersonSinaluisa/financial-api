package com.andersonsinaluisa.financial_api.core.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Builder
@Getter
public class CashFlowReport {
    public Long id;

    public LocalDateTime report_date;

    public double starting_balance;

    public double total_income;

    public double total_expense;

    public double ending_balance;

    public LocalDateTime created_at;
}
