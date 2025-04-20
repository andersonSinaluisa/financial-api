package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="financial_report")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class FinancialReportEntity {
    @Id
    public Long id;
    public String reportType;
    public LocalDateTime generatedAt;
    public String comments;
}
