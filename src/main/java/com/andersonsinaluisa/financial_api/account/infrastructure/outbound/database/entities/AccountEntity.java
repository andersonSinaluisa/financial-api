package com.andersonsinaluisa.financial_api.account.infrastructure.outbound.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
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
}
