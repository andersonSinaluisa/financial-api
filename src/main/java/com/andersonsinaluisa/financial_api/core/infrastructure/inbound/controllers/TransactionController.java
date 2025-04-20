package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.controllers;


import com.andersonsinaluisa.financial_api.core.application.calculate.BalanceCalculateUseCase;
import com.andersonsinaluisa.financial_api.core.application.create.TransactionCreateUseCase;
import com.andersonsinaluisa.financial_api.core.application.delete.TransactionDeleteUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.TransactionFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.update.TransactionUpdateUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TotalSumaryDto;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TransactionCreateDto;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TransactionDto;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.mappers.TransactionMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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

    @Autowired
    private final BalanceCalculateUseCase balanceCalculateUseCase;

    @GetMapping
    public Mono<ResponseEntity<Page<TransactionDto>>> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return transactionFindUseCase.all(pageable, start_date, end_date)
                .map(transactionsPage -> transactionsPage.map(TransactionMappers::fromDomainToDto))
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<TransactionDto>> create(@RequestBody TransactionCreateDto data) {
        return createUseCase.create(TransactionMappers.fromDtoToDomain(data))
                .map(TransactionMappers::fromDomainToDto)
                .map(ResponseEntity::ok);
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<TransactionDto>> update(@PathVariable long id,
                                                       @RequestBody TransactionCreateDto data) {
        Transaction transaction = TransactionMappers.fromDtoToDomain(data);
        transaction.id = id;
        return transactionUpdateUseCase.update(transaction)
                .map(TransactionMappers::fromDomainToDto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable long id) {
        return deleteUseCase.delete(id)
                .thenReturn(ResponseEntity.ok().<Void>build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TransactionDto>> get(@PathVariable long id) {
        return transactionFindUseCase.getById(id)
                .map(TransactionMappers::fromDomainToDto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/total-summary")
    public Mono<ResponseEntity<TotalSumaryDto>> getTotalSummary() {
        return balanceCalculateUseCase.calculateCurrentBalance()
                .map(ResponseEntity::ok);
    }




}
