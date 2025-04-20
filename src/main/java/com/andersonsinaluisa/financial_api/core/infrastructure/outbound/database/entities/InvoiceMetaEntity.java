package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

@Table(name="invoice_meta")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class InvoiceMetaEntity {

    @Id
    public Long id;

    public String meta_key;

    public String meta_value;

    public Long invoiceId;


}
