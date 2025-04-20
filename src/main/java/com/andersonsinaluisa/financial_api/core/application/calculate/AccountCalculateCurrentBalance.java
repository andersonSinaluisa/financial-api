package com.andersonsinaluisa.financial_api.core.application.calculate;

import com.andersonsinaluisa.financial_api.core.application.find.AccountFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.update.AccountUpdateUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.objectValues.TypeTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountCalculateCurrentBalance {

    @Autowired
    private AccountUpdateUseCase accountUpdateUseCase;

    @Autowired
    private AccountFindUseCase accountFindUseCase;

    public Mono<Void> calculateFromTransaction(Transaction transaction) {
        // Manejar cuenta origen
        if (transaction.source_account != null) {
            return accountFindUseCase.findById(transaction.source_account)
                    .flatMap(account_source -> {
                        double adjustment = calculateAdjustmentForSource(transaction);
                        if (canApplyTransaction(account_source, adjustment)) {
                            account_source.current_balance += adjustment;
                            return accountUpdateUseCase.update(account_source).then();
                        } else {
                            return Mono.error(new IllegalArgumentException("Saldo insuficiente en la cuenta origen: " + account_source.id));
                        }
                    })
                    .then(Mono.defer(() -> handleDestinationAccount(transaction)));

        } else {
            // Si no hay cuenta origen, solo manejar la cuenta destino
            return handleDestinationAccount(transaction);
        }
    }

    private Mono<Void> handleDestinationAccount(Transaction transaction) {
        if (transaction.destination_account != null) {
            return accountFindUseCase.findById(transaction.destination_account)
                    .flatMap(destination_account -> {
                        double adjustment = calculateAdjustmentForDestination(transaction);
                        destination_account.current_balance += adjustment;
                        return accountUpdateUseCase.update(destination_account).then();
                    });
        }
        return Mono.empty(); // Si no hay cuenta destino, devolvemos Mono vacío
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
