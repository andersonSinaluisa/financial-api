package com.andersonsinaluisa.financial_api.core.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ExpenseSummary {
    public Long id;

    public String category;

    public double total_expense;

    public LocalDateTime report_date;

    public LocalDateTime created_at;
}