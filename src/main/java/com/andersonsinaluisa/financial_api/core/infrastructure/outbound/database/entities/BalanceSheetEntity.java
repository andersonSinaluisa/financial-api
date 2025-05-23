package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="balance_sheet_entity")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class BalanceSheetEntity {
    @Id
    public Long id;

    public double assets;

    public double liabilities;

    public LocalDateTime reportDate;

    public LocalDateTime createdAt;
}
