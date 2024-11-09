package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="account")
@Builder
@Getter
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String account_name;

    public String account_type;

    public String account_number;

    public String initial_balance;

    public String status;

    public LocalDateTime created_at;

    public LocalDateTime updated_at;

    public boolean deleted;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "destination_account"

    )

    public List<TransactionEntity> destination_account;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "source_account"
    )

    public List<TransactionEntity> source_account;

}
