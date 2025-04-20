package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name="expense_sumary")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class ExpenseSummaryEntity {

    @Id
    public Long id;

    public String category;

    public double totalExpense;

    public LocalDateTime reportDate;

    public LocalDateTime createdAt;
    public LocalDate startDate;

    public LocalDate endDate;
}
