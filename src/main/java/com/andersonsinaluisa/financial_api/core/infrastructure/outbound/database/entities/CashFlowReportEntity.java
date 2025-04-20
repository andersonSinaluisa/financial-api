package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="cash_flow_report")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class CashFlowReportEntity {
    @Id
    public Long id;

    public LocalDateTime reportDate;

    public double startingBalance;

    public double totalIncome;

    public double totalExpense;

    public double endingBalance;

    public LocalDateTime createdAt;
}
