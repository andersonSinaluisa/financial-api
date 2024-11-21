package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.jobs;

import com.andersonsinaluisa.financial_api.core.application.create.ExpenseSumaryCreateUseCase;
import com.andersonsinaluisa.financial_api.core.application.create.IncomeSumaryCreateUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.AccountFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.TransactionFindUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database.entities.ExpenseSummaryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SumaryIncomeAndExpenseJob {

    @Autowired
    private final IncomeSumaryCreateUseCase incomeSumaryCreateUseCase;

    @Autowired
    private final TransactionFindUseCase transactionFindUseCase;

    @Autowired
    private final AccountFindUseCase accountFindUseCase;


    @Autowired
    private final ExpenseSumaryCreateUseCase expenseSumaryCreateUseCase;
    @Scheduled(cron = "20 * * * * *")
    public void execute() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate StartDate = LocalDate.now();

        List<Account> accounts = accountFindUseCase.findAll();

        for (Account _account : accounts) {
            List<Transaction> transactionAccount = transactionFindUseCase
                    .getByAccountAndMonthAndYear(
                            now.getMonthValue(), now.getYear(), _account.id
                    );
            incomeSumaryCreateUseCase.createFromTransaction(transactionAccount,_account,
                    StartDate,
                    now.toLocalDate());
            expenseSumaryCreateUseCase.createFromTransaction(transactionAccount,_account,StartDate,now.toLocalDate());

        }

    }
}
