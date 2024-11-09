package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name="expense_sumary")
@Builder
@Getter
public class ExpenseSummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String category;

    public double total_expense;

    public LocalDateTime report_date;

    public LocalDateTime created_at;
}
