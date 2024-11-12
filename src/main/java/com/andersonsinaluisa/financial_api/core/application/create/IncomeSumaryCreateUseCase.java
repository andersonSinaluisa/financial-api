package com.andersonsinaluisa.financial_api.core.application.create;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.model.TypeTransaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeSumaryCreateUseCase {

    @Autowired
    private final IncomeSumaryRepository incomeSumaryRepository;


    public IncomeSumary create(IncomeSumary data){
        return incomeSumaryRepository.create(data).orElseThrow();
    }


    public double calculateTotalIncome( List<Transaction> transactions){
        double total_account = 0;
        for(Transaction transaction: transactions) {
            if (transaction.transaction_type.equals(TypeTransaction.INGRESO.getValue())) {
                total_account += transaction.amount;
            } else if (transaction.transaction_type.equals(TypeTransaction.AJUSTE.getValue())) {
                // Aplicar el ajuste (puede ser positivo o negativo)
                total_account += transaction.amount;
            } else if (transaction.transaction_type.equals(TypeTransaction.DEPOSITO.getValue())) {
                // Suma el depósito a la cuenta
                total_account += transaction.amount;
            } else if (transaction.transaction_type.equals(TypeTransaction.REEMBOLSO.getValue())) {
                // Suma el reembolso (devolución de dinero)
                total_account += transaction.amount;
            }

        }
        return total_account;
    }

    public IncomeSumary createFromTransaction(
            List<Transaction> transactions,
            Account account,
            LocalDate start,
            LocalDate end
            ){
        double total_account = calculateTotalIncome(transactions);

        IncomeSumary incomeSumary = IncomeSumary.builder()
                .total_income(total_account)
                .created_at(LocalDateTime.now())
                .start_date(start)
                .end_date(end)
                .category(account.account_name)
                .report_date(LocalDateTime.now()).build();

        return incomeSumaryRepository.create(incomeSumary).orElseThrow();
    }



}
