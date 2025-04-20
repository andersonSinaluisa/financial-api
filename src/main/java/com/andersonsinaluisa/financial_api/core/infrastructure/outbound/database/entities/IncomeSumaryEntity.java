package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name="income_summary")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class IncomeSumaryEntity {
    @Id
    public Long id;

    public String category;

    public double totalIncome;

    public LocalDateTime reportDate;

    public LocalDateTime createdAt;

    public LocalDate startDate;

    public LocalDate endDate;

}
