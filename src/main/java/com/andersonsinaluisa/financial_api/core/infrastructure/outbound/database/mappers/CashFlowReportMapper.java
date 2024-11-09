package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.CashFlowReportEntity;

public class CashFlowReportMapper {

    public static CashFlowReport fromEntityToDomain(CashFlowReportEntity data){
        return CashFlowReport.builder()
                .id(data.id)
                .report_date(data.report_date)
                .starting_balance(data.starting_balance)
                .total_expense(data.total_expense)
                .total_income(data.total_income)
                .ending_balance(data.ending_balance)
                .created_at(data.created_at)
                .build();
    }

    public static CashFlowReportEntity fromDomainToEntity(CashFlowReport data){
        return CashFlowReportEntity.builder()
                .report_date(data.report_date)
                .starting_balance(data.starting_balance)
                .total_expense(data.total_expense)
                .total_income(data.total_income)
                .ending_balance(data.ending_balance)
                .created_at(data.created_at)
                .id(data.id)
                .build();
    }
}
