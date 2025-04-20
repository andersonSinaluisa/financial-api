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
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<Page<AccountDto>>> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            @RequestParam(defaultValue = "") String search
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return accountFindUseCase.findAll(pageable, search)
                .map(accountPage -> accountPage.map(AccountMappers::fromDomainToDto))
                .map(ResponseEntity::ok);
    }



    @GetMapping("/{slug}")
    public Mono<ResponseEntity<AccountDto>> get(@PathVariable("slug") String slug){
        return accountFindUseCase.findBySlug(slug).map(AccountMappers::fromDomainToDto).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<AccountDto>> create(@RequestBody AccountCreateDto data){
        Account a = AccountMappers.fromDtoToDomain(data);
        return accountCreateUseCase.create(a).map(AccountMappers::fromDomainToDto).map(ResponseEntity::ok);
    }


    @PatchMapping("/{id}")
    public Mono<ResponseEntity<AccountDto>> update(@PathVariable("id") long id,@RequestBody AccountCreateDto data){
        Account a = AccountMappers.fromDtoToDomain(data);
        a.id = id;
        return accountUpdateUseCase.update(a).map(AccountMappers::fromDomainToDto).map(ResponseEntity::ok);
    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") long id){

        return  deleteUseCase.deleteOne(id).map(ResponseEntity::ok);
    }

}
