package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name="financial_report")
@Builder
@Getter
public class FinancialReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String report_type;
    public LocalDateTime generated_at;
    public String comments;
}
