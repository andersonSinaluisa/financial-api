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
                .created_at(data.created_at)
                .source_account(data.source_account==null?0:data.source_account.id)
                .transaction_date(data.transaction_date)
                .transaction_type(data.transaction_type)
                .deleted(data.deleted)
                .destination_account(data.destination_account==null?0:data.destination_account.id)
                .build();

    }


    public static TransactionEntity fromDtoToDomain(Transaction data){
        return TransactionEntity.builder()
                .id(data.id)
                .transaction_date(data.transaction_date)
                .transaction_type(data.transaction_type)
                .created_at(data.created_at)
                .amount(data.amount)
                .source_account_id(data.source_account)
                .destination_account_id(data.destination_account)
                .category(data.category)
                .currency(data.currency)
                .description(data.description)
                .deleted(data.deleted)
                .build();
    }
}
