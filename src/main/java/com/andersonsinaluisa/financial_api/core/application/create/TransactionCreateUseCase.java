package com.andersonsinaluisa.financial_api.core.application.create;

import com.andersonsinaluisa.financial_api.core.domain.exception.TypeTransactionNotAllow;
import com.andersonsinaluisa.financial_api.core.domain.model.CashFlowReport;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.model.TypeTransaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionCreateUseCase {
    @Autowired
    private TransactionRepository transactionRepository;


    public Transaction create(Transaction data) throws Exception {
        List<TypeTransaction> avaliable = Arrays.stream(TypeTransaction.values()).toList();
        if(avaliable.stream().noneMatch(e->e.getValue().equals(data.transaction_type))){
            throw new TypeTransactionNotAllow("Tipo de transación no valida");
        }
        data.deleted = false;
        Transaction t =  this.transactionRepository.create(data).orElseThrow();
        return t;
    }
}
