package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import jakarta.persistence.*;
import lombok.*;

@Table(name="invoice_meta")
@Entity
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class InvoiceMetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String meta_key;

    public String meta_value;

    @ManyToOne
    @JoinColumn(name="invoice")
    public InvoiceEntity invoice;


}
