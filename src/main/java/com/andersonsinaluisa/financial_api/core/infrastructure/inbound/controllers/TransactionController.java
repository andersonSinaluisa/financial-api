package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.controllers;


import com.andersonsinaluisa.financial_api.core.application.create.TransactionCreateUseCase;
import com.andersonsinaluisa.financial_api.core.application.delete.TransactionDeleteUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.TransactionFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.update.TransactionUpdateUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TransactionCreateDto;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TransactionDto;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.mappers.TransactionMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private final TransactionCreateUseCase createUseCase;

    @Autowired
    private final TransactionFindUseCase transactionFindUseCase;

    @Autowired
    private final TransactionDeleteUseCase deleteUseCase;

    @Autowired
    private final TransactionUpdateUseCase transactionUpdateUseCase;


    @GetMapping
    public ResponseEntity<List<TransactionDto>> all(){

       List<TransactionDto> d= transactionFindUseCase.all().stream().map(TransactionMappers::fromDomainToDto).toList();
       return ResponseEntity.ok(d);
    }

    @PostMapping
    public ResponseEntity<TransactionDto> create(@RequestBody TransactionCreateDto data){
        Transaction a = createUseCase.create(TransactionMappers.fromDtoToDomain(data));

        return ResponseEntity.ok(TransactionMappers.fromDomainToDto(a));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<TransactionDto> update(@PathVariable("id") long id,
                                                 @RequestBody TransactionCreateDto data){
        Transaction transaction = TransactionMappers.fromDtoToDomain(data);
        transaction.id = id;
        transaction = transactionUpdateUseCase.update(transaction);
        return ResponseEntity.ok(TransactionMappers.fromDomainToDto(transaction));
    }






}
