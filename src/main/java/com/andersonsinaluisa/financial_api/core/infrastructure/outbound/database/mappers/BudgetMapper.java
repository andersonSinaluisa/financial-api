package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.Budget;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.BudgetEntity;

public class BudgetMapper {

    static Budget fromEntityToDomain(BudgetEntity data){
        return Budget.builder()
                .id(data.id)
                .assigned_budget(data.assigned_budget)
                .remaining_budget(data.remaining_budget)
                .project(data.project)
                .spent_amount(data.spent_amount)
                .created_at(data.created_at)
                .department(data.department)
                .build();
    }


    static BudgetEntity fromDomainToEntity(Budget data){
        return BudgetEntity.builder()
                .assigned_budget(data.assigned_budget)
                .remaining_budget(data.remaining_budget)
                .project(data.project)
                .spent_amount(data.spent_amount)
                .created_at(data.created_at)
                .department(data.department)
                .build();
    }
}
