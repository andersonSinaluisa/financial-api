package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.IncomeSumaryEntity;

public class IncomeSummaryMapper {

    public static IncomeSumaryEntity fromDomainToEntity(IncomeSumary data){
        return IncomeSumaryEntity.builder()
                .id(data.id)
                .total_income(data.total_income)
                .category(data.category)
                .report_date(data.report_date)
                .created_at(data.created_at)
                .end_date(data.end_date)
                .start_date(data.start_date)
                .build();
    }

    public static IncomeSumary fromEntityToDomain(IncomeSumaryEntity data){
        return IncomeSumary.builder()
                .created_at(data.created_at)
                .report_date(data.report_date)
                .category(data.category)
                .total_income(data.total_income)
                .id(data.id)
                .end_date(data.end_date)
                .start_date(data.start_date)
                .build();
    }
}
