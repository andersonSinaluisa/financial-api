package com.andersonsinaluisa.financial_api.core.domain.model;

import java.time.LocalDateTime;

public class FinancialReport {
    public Long id;
    public String report_type;
    public LocalDateTime generated_at;
    public String comments;
}