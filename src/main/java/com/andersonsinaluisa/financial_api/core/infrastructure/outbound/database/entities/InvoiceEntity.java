package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;


import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Table(name="invoice")
@Entity
@Builder
@Getter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public double amount;

    public String doc_number;

    public String num_at_card;

    public LocalDateTime due_date;

    public String status;

    public String type;

    @ManyToOne
    @JoinColumn(name = "account")
    public AccountEntity account;

    public LocalDateTime created_at;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "invoice"
    )
    public List<InvoiceMetaEntity> metaEntityList;


}
