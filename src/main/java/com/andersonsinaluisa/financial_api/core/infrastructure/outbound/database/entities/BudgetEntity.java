package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Table(name="budget")
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class BudgetEntity {

    @Id
    public Long id;

    public String department;

    public String project;

    public double assignedBudget;

    public double spentAmount;

    public double remainingBudget;

    public LocalDateTime createdAt;
}
