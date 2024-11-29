package com.andersonsinaluisa.financial_api.core.application.create;


import com.andersonsinaluisa.financial_api.core.domain.model.*;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseSumaryCreateUseCase {

    @Autowired
    private ExpenseSumaryRepository expenseSumaryRepository;

    public ExpenseSummary create(ExpenseSummary data){
        return expenseSumaryRepository.create(data).orElseThrow();
    }


    public double calculateTotaExpense(Transaction transaction){
        Optional<ExpenseSummary> incomeSumaryOptional = expenseSumaryRepository.getLast();
        double total_account = 0;

        if(incomeSumaryOptional.isPresent()){
            ExpenseSummary expenseSummary = incomeSumaryOptional.get();
            total_account = expenseSummary.total_expense;

        }
        if (transaction.transaction_type.equals(TypeTransaction.GASTO.getValue())) {
            total_account -= transaction.amount;
        } else if (transaction.transaction_type.equals(TypeTransaction.PAGO.getValue())) {
            // Aplicar el ajuste (puede ser positivo o negativo)
            total_account -= transaction.amount;
        } else if (transaction.transaction_type.equals(TypeTransaction.RETIRO.getValue())) {
            // Suma el reembolso (devolución de dinero)
            total_account -= transaction.amount;
        }

        return total_account;
    }


}
