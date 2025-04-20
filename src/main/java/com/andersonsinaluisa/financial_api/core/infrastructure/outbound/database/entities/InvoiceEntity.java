package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Table(name="invoice")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class InvoiceEntity {

    @Id
    public Long id;

    public double amount;

    public String docNumber;

    public String numAtCard;

    public LocalDateTime dueDate;

    public String status;

    public String type;

    public Long accountId;

    public LocalDateTime createdAt;



}
