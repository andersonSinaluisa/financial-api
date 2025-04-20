package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="assets_liabilities")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class AssetsLiabilitiesEntity {
    @Id
    public Long id;

    public String type;

    public String name;

    public double value;

    public double deprecationRate;

    public String location;

    public LocalDate acquiredDate;

    public LocalDateTime createdAt;

    public Long accountId;

}
