package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="account")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String account_name;

    public String account_type;

    public String account_number;

    public double initial_balance;

    public double current_balance;

    public String status;

    public LocalDateTime created_at;

    public LocalDateTime updated_at;

    public boolean deleted;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "destination_account"

    )

    public List<TransactionEntity> destination_transaction;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "source_account"
    )

    public List<TransactionEntity> source_transaction;


    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "account"
    )
    public List<InvoiceEntity> invoices;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "account"
    )
    public List<AssetsLiabilitiesEntity> assetsLiabilities;



}
