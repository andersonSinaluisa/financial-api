package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDateTime;
@Table(name="assets_movement")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class AssetsMovementEntity {
    @Id
    public Long id;


    public Long assetId;


    public LocalDateTime movementDate;

    public String description;

    public double amount;

    public LocalDateTime createdAt;

}
