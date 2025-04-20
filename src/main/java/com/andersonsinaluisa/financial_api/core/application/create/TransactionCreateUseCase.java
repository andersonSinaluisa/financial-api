package com.andersonsinaluisa.financial_api.core.application.create;

import com.andersonsinaluisa.financial_api.core.application.calculate.AccountCalculateCurrentBalance;
import com.andersonsinaluisa.financial_api.core.domain.exception.InvalidDataTransaction;
import com.andersonsinaluisa.financial_api.core.domain.exception.InvalidDateTransaction;
import com.andersonsinaluisa.financial_api.core.domain.exception.TypeTransactionNotAllow;
import com.andersonsinaluisa.financial_api.core.domain.model.*;
import com.andersonsinaluisa.financial_api.core.domain.objectValues.TypeTransaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


    public Mono<Transaction> create(Transaction data) {
        // Validación del tipo de transacción
        if (!TypeTransaction.isValidType(data.transaction_type)) {
            return Mono.error(new TypeTransactionNotAllow("Tipo de transacción no válida"));
        }

        // Validación si es tipo TRANSFERENCIA
        if (data.transaction_type.equals(TypeTransaction.TRANSFERENCIA.getValue())) {
            if (data.destination_account == null || data.source_account == null) {
                return Mono.error(new InvalidDataTransaction("Verifique la cuenta de destino y la cuenta de origen"));
            }
        }

        // Validación de la fecha
        LocalDateTime now = LocalDateTime.now();
        if (data.transaction_date.isAfter(now)) {
            return Mono.error(new InvalidDateTransaction("La fecha de la transacción no debe ser mayor a la fecha actual"));
        }

        // Generar el identificador único
        data.identifier = UUID.randomUUID();

        // Crear la transacción
        return transactionRepository.create(data)
                .flatMap(t -> {
                    // Calcular el balance actual y las sumas de ingresos y gastos
                    return Mono.zip(
                            accountCalculateCurrentBalance.calculateFromTransaction(t),
                            incomeSumaryCreateUseCase.calculateTotalIncome(t),
                            expenseSumaryCreateUseCase.calculateTotalExpense(t)
                    ).flatMap(tuple -> {
                        // Crear las sumas de ingresos y gastos
                        Double totalIncome = tuple.getT2();
                        Double totalExpense = tuple.getT3();

                        // Crear los objetos IncomeSumary y ExpenseSummary
                        IncomeSumary income = IncomeSumary.builder()
                                .total_income(totalIncome)
                                .start_date(LocalDate.now())
                                .end_date(LocalDate.now())
                                .created_at(LocalDateTime.now())
                                .report_date(LocalDateTime.now())
                                .build();

                        ExpenseSummary expense = ExpenseSummary.builder()
                                .total_expense(totalExpense)
                                .category(t.category)
                                .start_date(LocalDate.now())
                                .end_date(LocalDate.now())
                                .created_at(LocalDateTime.now())
                                .build();

                        // Crear las sumas de ingresos y gastos en la base de datos
                        return Mono.zip(
                                incomeSumaryCreateUseCase.create(income),
                                expenseSumaryCreateUseCase.create(expense)
                        ).thenReturn(t); // Devolver la transacción creada
                    });
                });
    }

}
