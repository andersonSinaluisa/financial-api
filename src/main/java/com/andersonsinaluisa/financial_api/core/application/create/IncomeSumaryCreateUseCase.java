package com.andersonsinaluisa.financial_api.core.application.create;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.objectValues.TypeTransaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class IncomeSumaryCreateUseCase {

    @Autowired
    private final IncomeSumaryRepository incomeSumaryRepository;


    public Mono<IncomeSumary> create(IncomeSumary data){
        return incomeSumaryRepository.create(data);
    }


    public Mono<Double> calculateTotalIncome(Transaction transaction) {
        return incomeSumaryRepository.getLast()
                .map(IncomeSumary::getTotal_income)
                .defaultIfEmpty(0.0)
                .map(total -> {
                    double result = total;

                    if (transaction.transaction_type.equals(TypeTransaction.INGRESO.getValue()) ||
                            transaction.transaction_type.equals(TypeTransaction.AJUSTE.getValue()) ||
                            transaction.transaction_type.equals(TypeTransaction.DEPOSITO.getValue()) ||
                            transaction.transaction_type.equals(TypeTransaction.REEMBOLSO.getValue())) {
                        result += transaction.amount;
                    }

                    return result;
                });
    }





}
