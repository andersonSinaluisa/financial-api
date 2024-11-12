package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="transaction")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public LocalDateTime transaction_date;

    public double amount;

    public String category;

    @ManyToOne
    @JoinColumn(name="source_account")
    public AccountEntity source_account;

    @ManyToOne
    @JoinColumn(name="destination_account")
    public AccountEntity destination_account;

    public Long source_account_id;

    public Long destination_account_id;

    public String description;

    public String currency;

    public String transaction_type;

    public LocalDateTime created_at;
}