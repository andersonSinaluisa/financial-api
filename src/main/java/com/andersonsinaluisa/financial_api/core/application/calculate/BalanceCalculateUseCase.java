package com.andersonsinaluisa.financial_api.core.application.calculate;

import com.andersonsinaluisa.financial_api.core.application.create.ExpenseSumaryCreateUseCase;
import com.andersonsinaluisa.financial_api.core.application.create.IncomeSumaryCreateUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.TransactionFindUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceCalculateUseCase {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private IncomeSumaryCreateUseCase incomeSumaryCreateUseCase;

    @Autowired
    private ExpenseSumaryCreateUseCase expenseSumaryCreateUseCase;

    @Autowired
    private TransactionFindUseCase transactionFindUseCase;

    public double calculateCurrentBalance(
            LocalDate start_date,
            LocalDate end_date
    ){
        List<Account> accountList = repository.all();

        double total_account = 0;
        for(Account account:accountList){
            total_account += account.initial_balance;
        }
        List<Transaction> transactions = transactionFindUseCase.getByRange(start_date,end_date);

        double total_income = incomeSumaryCreateUseCase.calculateTotalIncome(transactions);
        double total_expense = expenseSumaryCreateUseCase.calculateTotaExpense(transactions);


        return total_account + total_income - total_expense;


    }
}
