package com.andersonsinaluisa.financial_api.core.application.calculate;

import com.andersonsinaluisa.financial_api.core.application.find.ExpenseSumaryFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.IncomeSumaryFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.TransactionFindUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TotalSumaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceCalculateUseCase {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private IncomeSumaryFindUseCase incomeSumaryFindUseCase;

    @Autowired
    private ExpenseSumaryFindUseCase expenseSumaryFindUseCase;

    @Autowired
    private TransactionFindUseCase transactionFindUseCase;

    public Mono<TotalSumaryDto> calculateCurrentBalance(){
        Mono<Double> totalAccountMono = repository.all()
                .map(Account::getInitial_balance)
                .reduce(0.0, Double::sum);


        Mono<Double> totalIncomeMono = incomeSumaryFindUseCase.getCurrent()
                .map(e-> e.total_income);


        Mono<Double> totalExpenseMono = expenseSumaryFindUseCase.getCurrent()
                .map(e->e.total_expense);



        return Mono.zip(totalAccountMono,totalIncomeMono,totalExpenseMono).map(tuple ->{
            double totalAccount = tuple.getT1();
            double totalIncome = tuple.getT2();
            double totalExpense = tuple.getT3();
            double balance = totalAccount + totalIncome - totalExpense;

            return TotalSumaryDto.builder()
                    .totalIncome(totalIncome)
                    .totalExpense(totalExpense)
                    .balance(balance)
                    .build();
        });


    }







}
