package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TotalSumaryDto {
    public double totalIncome;

    public double totalExpense;

    public double balance;
}
