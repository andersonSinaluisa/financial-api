package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="income_summary")
@Builder
@Getter
public class IncomeSumaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String category;

    public double total_income;

    public LocalDateTime report_date;

    public LocalDateTime created_at;

    public LocalDate start_date;

    public LocalDate end_date;

}
