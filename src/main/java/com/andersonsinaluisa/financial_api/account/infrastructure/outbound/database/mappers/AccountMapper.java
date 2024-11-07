package com.andersonsinaluisa.financial_api.account.infrastructure.outbound.database.mappers;

import com.andersonsinaluisa.financial_api.account.domain.model.Account;
import com.andersonsinaluisa.financial_api.account.infrastructure.outbound.database.entities.AccountEntity;
import org.springframework.data.util.Optionals;

import java.util.Optional;

public abstract class AccountMapper {

    public static Account fromDomainToDto(AccountEntity data){
        Account obj = new Account();
        obj.account_name = data.account_name;
        obj.id = data.id;
        obj.account_number = data.account_number;
        obj.account_type = data.account_type;
        obj.created_at = data.created_at;
        obj.initial_balance = data.initial_balance;
        obj.status = data.status;
        obj.updated_at = data.updated_at;

        return obj;
    }

    public static AccountEntity fromDtoToDomain(Account data){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.id = data.id;
        accountEntity.account_name = data.account_name;
        accountEntity.account_number = data.account_number;
        accountEntity.account_type = data.account_type;
        accountEntity.created_at = data.created_at;
        accountEntity.status = data.status;
        accountEntity.initial_balance = data.initial_balance;
        accountEntity.updated_at = data.updated_at;
        return accountEntity;
    }




}
