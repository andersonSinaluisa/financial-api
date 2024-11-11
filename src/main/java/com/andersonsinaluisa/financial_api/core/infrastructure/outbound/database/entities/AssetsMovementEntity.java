package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Entity
@Table(name="assets_movement")
@Builder
@Getter
public class AssetsMovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name="asset")
    public AssetsLiabilitiesEntity asset;


    public LocalDateTime movement_date;

    public String description;

    public double amount;

    public LocalDateTime created_at;

}
