package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@Table(name="budget")
@Entity
public class BudgetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String department;

    public String project;

    public double assigned_budget;

    public double spent_amount;

    public double remaining_budget;

    public LocalDateTime created_at;
}