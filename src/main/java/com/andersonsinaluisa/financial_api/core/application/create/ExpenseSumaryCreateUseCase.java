package com.andersonsinaluisa.financial_api.core.application.create;


import com.andersonsinaluisa.financial_api.core.domain.model.*;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseSumaryCreateUseCase {

    @Autowired
    private ExpenseSumaryRepository expenseSumaryRepository;

    public ExpenseSummary create(ExpenseSummary data){
        return expenseSumaryRepository.create(data).orElseThrow();
    }

    public ExpenseSummary createFromTransaction(
            List<Transaction> transactions,
            Account account,
            LocalDate start,
            LocalDate end
    ){
        double total_account = 0;
        for(Transaction transaction: transactions) {
            if (transaction.transaction_type.equals(TypeTransaction.GASTO.getValue())) {
                total_account -= transaction.amount;
            } else if (transaction.transaction_type.equals(TypeTransaction.PAGO.getValue())) {
                // Aplicar el ajuste (puede ser positivo o negativo)
                total_account -= transaction.amount;
            } else if (transaction.transaction_type.equals(TypeTransaction.RETIRO.getValue())) {
                // Suma el reembolso (devolución de dinero)
                total_account -= transaction.amount;
            }

        }
        ExpenseSummary expenseSummary = ExpenseSummary.builder()
                .category(account.account_name)
                .total_expense(total_account)
                .report_date(LocalDateTime.now())
                .created_at(LocalDateTime.now())
                .start_date(start)
                .end_date(end)
                .build();

        return create(expenseSummary);

    }

}
