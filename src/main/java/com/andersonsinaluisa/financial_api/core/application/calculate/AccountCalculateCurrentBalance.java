package com.andersonsinaluisa.financial_api.core.application.calculate;

import com.andersonsinaluisa.financial_api.core.application.find.AccountFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.update.AccountUpdateUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.model.TypeTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AccountCalculateCurrentBalance {

    @Autowired
    private AccountUpdateUseCase accountUpdateUseCase;

    @Autowired
    private AccountFindUseCase accountFindUseCase;

    public synchronized void calculateFromTransaction(Transaction transaction) {
        // Manejar cuenta origen
        if (transaction.source_account != null) {
            //encontrar la cuenta a afectar
            accountFindUseCase.findById(transaction.source_account).ifPresent(account_source -> {
                //obtener la operacion a realizar (suma o resta)
                double adjustment = calculateAdjustmentForSource(transaction);
                if (canApplyTransaction(account_source, adjustment)) {
                    account_source.current_balance += adjustment;
                    accountUpdateUseCase.update(account_source);
                } else {
                    throw new IllegalArgumentException("Saldo insuficiente en la cuenta origen: " + account_source.id);
                }
            });
        }

        // Manejar cuenta destino
        if (transaction.destination_account != null) {
            accountFindUseCase.findById(transaction.destination_account).ifPresent(destination_account -> {
                double adjustment = calculateAdjustmentForDestination(transaction);
                destination_account.current_balance += adjustment;
                accountUpdateUseCase.update(destination_account);
            });
        }
    }

    private double calculateAdjustmentForSource(Transaction transaction) {
        switch (TypeTransaction.valueOf(transaction.transaction_type)) {
            case PAGO:
            case TRANSFERENCIA:
            case GASTO:
            case REEMBOLSO:
            case RETIRO:
                return -transaction.amount;
            case AJUSTE:
                return transaction.amount;
            default:
                return 0;
        }
    }

    private double calculateAdjustmentForDestination(Transaction transaction) {
        switch (TypeTransaction.valueOf(transaction.transaction_type)) {
            case PAGO:
            case TRANSFERENCIA:
            case GASTO:
            case REEMBOLSO:
            case RETIRO:
            case INGRESO:
                return transaction.amount;
            case AJUSTE:
                return -transaction.amount;
            default:
                return 0;
        }
    }

    private boolean canApplyTransaction(Account account, double adjustment) {
        return account.current_balance + adjustment >= 0; // Validar si la cuenta puede quedar en negativo
    }


}
