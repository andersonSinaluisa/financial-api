package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.account.AccountCreateDto;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.account.AccountDto;
import com.github.slugify.Slugify;

import java.time.LocalDateTime;

public class AccountMappers {

    public static Account fromDtoToDomain(AccountCreateDto data){
            return Account.builder()
                    .account_name(data.account_name)
                    .account_type(data.account_type)
                    .account_number(data.account_number)
                    .status(data.status)
                    .initial_balance(data.initial_balance)
                    .created_at(LocalDateTime.now())
                    .build();
    }

    public static AccountDto fromDomainToDto(Account data){
        return AccountDto.builder()
                .id(data.id)
                .created_at(data.created_at)
                .updated_at(data.updated_at)
                .account_name(data.account_name)
                .account_number(data.account_number)
                .account_type(data.account_type)
                .initial_balance(data.initial_balance)
                .status(data.status)
                .current_balance(data.current_balance)
                .slug(data.slug)
                .build();
    }
}
