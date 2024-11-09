package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Table(name="balance_sheet_entity")
@Entity
@Builder
@Getter
public class BalanceSheetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public double assets;

    public double liabilities;

    public LocalDateTime report_date;

    public LocalDateTime created_at;
}
