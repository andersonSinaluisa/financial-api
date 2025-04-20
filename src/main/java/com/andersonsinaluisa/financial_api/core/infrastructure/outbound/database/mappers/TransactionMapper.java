package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.TransactionEntity;

public class TransactionMapper {


    public static Transaction fromDomainToDto(TransactionEntity data){
        return Transaction.builder()
                .id(data.id)
                .amount(data.amount)
                .category(data.category)
                .currency(data.currency)
                .description(data.description)
                .created_at(data.createdAt)
                .source_account(data.sourceAccount)
                .transaction_date(data.transactionDate)
                .transaction_type(data.transactionType)
                .deleted(data.deleted)
                .identifier(data.identifier)
                .destination_account(data.destinationAccountId)
                .build();

    }


    public static TransactionEntity fromDtoToDomain(Transaction data){
        return TransactionEntity.builder()
                .id(data.id)
                .transactionDate(data.transaction_date)
                .transactionType(data.transaction_type)
                .createdAt(data.created_at)
                .amount(data.amount)
                .identifier(data.identifier)
                .sourceAccount(data.source_account)
                .destinationAccountId(data.destination_account)
                .category(data.category)
                .currency(data.currency)
                .description(data.description)
                .deleted(data.deleted)
                .build();
    }
}
