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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<AccountDto>> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Account> list = accountFindUseCase.findAll(pageable);
        Page<AccountDto> listDto =  list.map(AccountMappers::fromDomainToDto);
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
