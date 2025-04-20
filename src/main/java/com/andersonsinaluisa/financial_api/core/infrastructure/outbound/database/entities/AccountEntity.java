package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Table(name="account")
@Builder
@Getter
@NoArgsConstructor // This will generate the default constructor// Generates a no-argument constructor
@AllArgsConstructor
@Setter
public class AccountEntity {
    @Id
    public Long id;

    public String account_name;

    public String account_type;

    public String account_number;

    public double initial_balance;

    public double current_balance;

    public String status;

    public LocalDateTime created_at;

    public LocalDateTime updated_at;

    public String slug;

    public boolean deleted;





}
