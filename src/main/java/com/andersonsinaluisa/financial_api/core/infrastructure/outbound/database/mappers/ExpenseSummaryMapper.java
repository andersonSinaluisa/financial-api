package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;

public class ExpenseSummaryMapper {

    public static ExpenseSummaryEntity fromDomainToEntity(ExpenseSummary data){
        return ExpenseSummaryEntity.builder()
                .id(data.id)
                .total_expense(data.total_expense)
                .category(data.category)
                .created_at(data.created_at)
                .report_date(data.report_date)
                .build();
    }

    public static ExpenseSummary fromEntityToDomain(ExpenseSummaryEntity data){
        return ExpenseSummary.builder()
                .report_date(data.report_date)
                .category(data.category)
                .id(data.id)
                .total_expense(data.total_expense)
                .created_at(data.created_at)
                .build();
    }
}
