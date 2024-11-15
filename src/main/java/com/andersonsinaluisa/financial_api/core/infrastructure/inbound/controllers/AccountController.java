package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.controllers;
import com.andersonsinaluisa.financial_api.core.application.create.AccountCreateUseCase;
import com.andersonsinaluisa.financial_api.core.application.delete.AccountDeleteUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.AccountFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.update.AccountUpdateUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.account.AccountCreateDto;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.account.AccountDto;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.mappers.AccountMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private final AccountFindUseCase accountFindUseCase;

    @Autowired
    private final AccountCreateUseCase accountCreateUseCase;

    @Autowired
    private final AccountUpdateUseCase accountUpdateUseCase;

    @Autowired
    private final AccountDeleteUseCase deleteUseCase;

    @GetMapping
    public ResponseEntity<List<AccountDto>> all(){
        List<Account> list = accountFindUseCase.findAll();
        List<AccountDto> listDto =  list.stream().map(AccountMappers::fromDomainToDto).toList();
        return ResponseEntity.ok(listDto);
    }


    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody AccountCreateDto data){
        Account a = AccountMappers.fromDtoToDomain(data);
        a = accountCreateUseCase.create(a);
        return ResponseEntity.ok(AccountMappers.fromDomainToDto(a));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable("id") long id,@RequestBody AccountCreateDto data){
        Account a = AccountMappers.fromDtoToDomain(data);
        a.id = id;
        a = accountUpdateUseCase.update(a);
        return ResponseEntity.ok(AccountMappers.fromDomainToDto(a));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id){
        deleteUseCase.deleteOne(id);
        return ResponseEntity.ok().build();
    }

}
