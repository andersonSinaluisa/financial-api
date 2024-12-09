package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TransactionCreateDto;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TransactionDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionMappers {

    public static Transaction fromDtoToDomain(TransactionCreateDto data){
        return Transaction.builder()
                .transaction_date(data.transaction_date)
                .transaction_type(data.transaction_type)
                .amount(data.amount)
                .identifier(UUID.randomUUID())
                .destination_account(data.destination_account_id)
                .source_account(data.source_account_id)
                .description(data.description)
                .currency(data.currency)
                .category(data.category)
                .created_at(LocalDateTime.now())
                .deleted(false)
                .build();
    }


    public static TransactionDto fromDomainToDto(Transaction data){
        return TransactionDto.builder()
                .id(data.id)
                .created_at(data.created_at)
                .amount(data.amount)
                .destination_account(data.destination_account)
                .source_account(data.source_account)
                .category(data.category)
                .currency(data.currency)
                .description(data.description)
                .transaction_date(data.transaction_date)
                .transaction_type(data.transaction_type)
                .identifier(data.identifier)
                .build();
    }

}
