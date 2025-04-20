package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.Budget;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.BudgetEntity;

public class BudgetMapper {

    static Budget fromEntityToDomain(BudgetEntity data){
        return Budget.builder()
                .id(data.id)
                .assigned_budget(data.assignedBudget)
                .remaining_budget(data.remainingBudget)
                .project(data.project)
                .spent_amount(data.spentAmount)
                .created_at(data.createdAt)
                .department(data.department)
                .build();
    }


    static BudgetEntity fromDomainToEntity(Budget data){
        return BudgetEntity.builder()
                .assignedBudget(data.assigned_budget)
                .remainingBudget(data.remaining_budget)
                .project(data.project)
                .spentAmount(data.spent_amount)
                .createdAt(data.created_at)
                .department(data.department)
                .build();
    }
}
