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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<Page<TransactionDto>> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            @RequestParam(defaultValue = "")LocalDate start_date,
            @RequestParam(defaultValue = "") LocalDate end_date
    ){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Transaction> d =  transactionFindUseCase.all(pageable,start_date,end_date);
        Page<TransactionDto> response = d.map(TransactionMappers::fromDomainToDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TransactionDto> create(@RequestBody TransactionCreateDto data) throws Exception {


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


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id){
        deleteUseCase.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> get(@PathVariable("id") long id){
        Transaction transaction = transactionFindUseCase.getById(id);
        return ResponseEntity.ok(TransactionMappers.fromDomainToDto(transaction));

    }




}
