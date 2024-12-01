package com.andersonsinaluisa.financial_api.core.application.create;

import com.andersonsinaluisa.financial_api.core.application.calculate.AccountCalculateCurrentBalance;
import com.andersonsinaluisa.financial_api.core.application.find.AccountFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.update.AccountUpdateUseCase;
import com.andersonsinaluisa.financial_api.core.domain.exception.InvalidDataTransaction;
import com.andersonsinaluisa.financial_api.core.domain.exception.InvalidDateTransaction;
import com.andersonsinaluisa.financial_api.core.domain.exception.TypeTransactionNotAllow;
import com.andersonsinaluisa.financial_api.core.domain.model.*;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionCreateUseCase {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private IncomeSumaryRepository incomeSumaryRepository;

    @Autowired ExpenseSumaryCreateUseCase expenseSumaryCreateUseCase;

    @Autowired
    private IncomeSumaryCreateUseCase incomeSumaryCreateUseCase;

    @Autowired
    private AccountCalculateCurrentBalance accountCalculateCurrentBalance;


    public Transaction create(Transaction data) throws Exception {
        List<TypeTransaction> avaliable = Arrays.stream(TypeTransaction.values()).toList();
        if(avaliable.stream().noneMatch(e->e.getValue().equals(data.transaction_type))){
            throw new TypeTransactionNotAllow("Tipo de transación no valida");
        }
        if(data.transaction_type == TypeTransaction.TRANSFERENCIA.getValue()){
            if(data.destination_account==null||data.source_account==null){
                throw new InvalidDataTransaction("Verifique la cuenta de destino y la cuenta de origen");
            }
        }

        if(data.transaction_date.isAfter(LocalDateTime.now())){
            throw  new InvalidDateTransaction("La fecha de la transacción no debe ser mayor a la fecha actual");
        }


        Transaction t =  this.transactionRepository.create(data).orElseThrow();

        accountCalculateCurrentBalance.calculateFromTransaction(t);

        double total_income = incomeSumaryCreateUseCase.calculateTotalIncome(t);
        double total_expense = expenseSumaryCreateUseCase.calculateTotaExpense(t);

        incomeSumaryCreateUseCase.create(IncomeSumary.builder().total_income(total_income)
                .start_date(LocalDate.now())
                .end_date(LocalDate.now())
                        .created_at(LocalDateTime.now())
                .report_date(LocalDateTime.now()).build());
        expenseSumaryCreateUseCase.create(ExpenseSummary.builder()
                        .total_expense(total_expense)
                        .category(t.category)
                        .created_at(LocalDateTime.now())
                        .start_date(LocalDate.now())
                        .created_at(LocalDateTime.now())
                        .end_date(LocalDate.now())
                .build());
        return t;
    }
}
