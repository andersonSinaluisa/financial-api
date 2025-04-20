package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Table(name="transaction")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class TransactionEntity {
    @Id
    public Long id;

    public LocalDateTime transactionDate;

    public double amount;

    public String category;

    public UUID identifier;
    public Long sourceAccount;

    public Long destinationAccount;

    public Long sourceAccountId;

    public Long destinationAccountId;

    public String description;

    public String currency;

    public String transactionType;

    public LocalDateTime createdAt;

    public boolean deleted;
}