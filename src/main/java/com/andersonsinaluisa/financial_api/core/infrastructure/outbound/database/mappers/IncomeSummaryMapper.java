package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.IncomeSumaryEntity;

public class IncomeSummaryMapper {

    public static IncomeSumaryEntity fromDomainToEntity(IncomeSumary data){
        return IncomeSumaryEntity.builder()
                .id(data.id)
                .totalIncome(data.total_income)
                .category(data.category)
                .reportDate(data.report_date)
                .createdAt(data.created_at)
                .endDate(data.end_date)
                .startDate(data.start_date)
                .build();
    }

    public static IncomeSumary fromEntityToDomain(IncomeSumaryEntity data){
        return IncomeSumary.builder()
                .created_at(data.createdAt)
                .report_date(data.reportDate)
                .category(data.category)
                .total_income(data.totalIncome)
                .id(data.id)
                .end_date(data.endDate)
                .start_date(data.startDate)
                .build();
    }
}
