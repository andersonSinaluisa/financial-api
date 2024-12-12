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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncomeSumaryCreateUseCase {

    @Autowired
    private final IncomeSumaryRepository incomeSumaryRepository;


    public IncomeSumary create(IncomeSumary data){
        return incomeSumaryRepository.create(data).orElseThrow();
    }


    public synchronized double calculateTotalIncome( Transaction transactions){

        Optional<IncomeSumary> incomeSumaryOptional = incomeSumaryRepository.getLast();
        double total_account = 0;

        if(incomeSumaryOptional.isPresent()){
            IncomeSumary incomeSumary = incomeSumaryOptional.get();
            total_account = incomeSumary.total_income;

        }
        if (transactions.transaction_type.equals(TypeTransaction.INGRESO.getValue())) {
            total_account += transactions.amount;
        } else if (transactions.transaction_type.equals(TypeTransaction.AJUSTE.getValue())) {
            // Aplicar el ajuste (puede ser positivo o negativo)
            total_account += transactions.amount;
        } else if (transactions.transaction_type.equals(TypeTransaction.DEPOSITO.getValue())) {
            // Suma el depósito a la cuenta
            total_account += transactions.amount;
        } else if (transactions.transaction_type.equals(TypeTransaction.REEMBOLSO.getValue())) {
            // Suma el reembolso (devolución de dinero)
            total_account += transactions.amount;
        }
        return total_account;
    }





}
