package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="assets_liabilities")
@Entity
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class AssetsLiabilitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String type;

    public String name;

    public double value;

    public double deprecation_rate;

    public String location;

    public LocalDate acquired_date;

    public LocalDateTime created_at;
    @ManyToOne
    @JoinColumn(name="account")
    public AccountEntity account;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "asset"
    )
    public List<AssetsMovementEntity> assetsMovements;
}
