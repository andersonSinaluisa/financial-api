package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;

public class ExpenseSummaryMapper {

    public static ExpenseSummaryEntity fromDomainToEntity(ExpenseSummary data){
        return ExpenseSummaryEntity.builder()
                .id(data.id)
                .totalExpense(data.total_expense)
                .category(data.category)
                .createdAt(data.created_at)
                .reportDate(data.report_date)
                .build();
    }

    public static ExpenseSummary fromEntityToDomain(ExpenseSummaryEntity data){
        return ExpenseSummary.builder()
                .report_date(data.reportDate)
                .category(data.category)
                .id(data.id)
                .total_expense(data.totalExpense)
                .created_at(data.createdAt)
                .build();
    }
}
