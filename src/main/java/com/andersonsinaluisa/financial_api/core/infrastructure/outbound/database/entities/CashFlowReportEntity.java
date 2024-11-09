package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name="cash_flow_report")
@Builder
@Getter
public class CashFlowReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public LocalDateTime report_date;

    public double starting_balance;

    public double total_income;

    public double total_expense;

    public double ending_balance;

    public LocalDateTime created_at;
}
