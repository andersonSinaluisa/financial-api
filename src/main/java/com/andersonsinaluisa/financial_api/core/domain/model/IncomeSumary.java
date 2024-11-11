package com.andersonsinaluisa.financial_api.core.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class IncomeSumary {

    public Long id;

    public String category;

    public double total_income;

    public LocalDateTime report_date;

    public LocalDateTime created_at;

    public LocalDate start_date;

    public LocalDate end_date;
}
