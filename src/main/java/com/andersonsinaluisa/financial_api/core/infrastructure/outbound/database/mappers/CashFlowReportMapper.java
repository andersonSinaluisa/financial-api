package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.CashFlowReportEntity;

public class CashFlowReportMapper {

    public static CashFlowReport fromEntityToDomain(CashFlowReportEntity data){
        return CashFlowReport.builder()
                .id(data.id)
                .report_date(data.reportDate)
                .starting_balance(data.startingBalance)
                .total_expense(data.totalExpense)
                .total_income(data.totalIncome)
                .ending_balance(data.endingBalance)
                .created_at(data.createdAt)
                .build();
    }

    public static CashFlowReportEntity fromDomainToEntity(CashFlowReport data){
        return CashFlowReportEntity.builder()
                .reportDate(data.report_date)
                .startingBalance(data.starting_balance)
                .totalExpense(data.total_expense)
                .totalIncome(data.total_income)
                .endingBalance(data.ending_balance)
                .createdAt(data.created_at)
                .id(data.id)
                .build();
    }
}
