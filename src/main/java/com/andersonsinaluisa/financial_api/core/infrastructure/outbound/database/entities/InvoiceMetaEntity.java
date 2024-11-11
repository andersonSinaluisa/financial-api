package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Table(name="invoice_meta")
@Entity
@Builder
@Getter
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
