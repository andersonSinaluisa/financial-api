package com.andersonsinaluisa.financial_api.core.application.create;


import com.andersonsinaluisa.financial_api.core.domain.model.*;
import com.andersonsinaluisa.financial_api.core.domain.objectValues.TypeTransaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExpenseSumaryCreateUseCase {

    @Autowired
    private ExpenseSumaryRepository expenseSumaryRepository;

    public Mono<ExpenseSummary> create(ExpenseSummary data){
        return expenseSumaryRepository.create(data);
    }


    public Mono<Double> calculateTotalExpense(Transaction transaction) {
        return expenseSumaryRepository.getLast()
                .map(e -> e.total_expense)
                .defaultIfEmpty(0.0)
                .map(total -> {
                    double result = total;

                    if (transaction.transaction_type.equals(TypeTransaction.GASTO.getValue())) {
                        result += transaction.amount;
                    } else if (transaction.transaction_type.equals(TypeTransaction.PAGO.getValue()) ||
                            transaction.transaction_type.equals(TypeTransaction.RETIRO.getValue())) {
                        result -= transaction.amount;
                    }

                    return result;
                });
    }



}
