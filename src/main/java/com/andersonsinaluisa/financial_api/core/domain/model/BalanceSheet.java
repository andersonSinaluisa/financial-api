package com.andersonsinaluisa.financial_api.core.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
@Getter
public class BalanceSheet {

    public Long id;

    public double assets;

    public double liabilities;

    public LocalDateTime report_date;

    public LocalDateTime created_at;
}
