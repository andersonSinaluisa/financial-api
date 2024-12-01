package com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.AccountEntity;


public abstract class AccountMapper {

    public static Account fromDomainToDto(AccountEntity data){

        Account obj = Account.builder().id(data.id)
                .account_name(data.account_name)
                .account_number(data.account_number)
                .account_type(data.account_type)
                .initial_balance(data.initial_balance)
                .status(data.status)
                .created_at(data.created_at)
                .slug(data.slug)
                .current_balance(data.current_balance)
                .updated_at(data.updated_at).build();

        return obj;
    }

    public static AccountEntity fromDtoToDomain(Account data){


        return AccountEntity.builder().id(data.id)
                .account_name(data.account_name)
                .account_number(data.account_number)
                .account_type(data.account_type)
                .initial_balance(data.initial_balance)
                .status(data.status)
                .created_at(data.created_at)
                .slug(data.slug)
                .current_balance(data.current_balance)
                .updated_at(data.updated_at).build();
    }




}
