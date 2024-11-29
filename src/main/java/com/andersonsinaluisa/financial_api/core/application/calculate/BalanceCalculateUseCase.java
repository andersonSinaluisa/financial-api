package com.andersonsinaluisa.financial_api.core.application.calculate;

import com.andersonsinaluisa.financial_api.core.application.create.ExpenseSumaryCreateUseCase;
import com.andersonsinaluisa.financial_api.core.application.create.IncomeSumaryCreateUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.ExpenseSumaryFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.IncomeSumaryFindUseCase;
import com.andersonsinaluisa.financial_api.core.application.find.TransactionFindUseCase;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.model.ExpenseSummary;
import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.domain.model.Transaction;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import com.andersonsinaluisa.financial_api.core.domain.repository.ExpenseSumaryRepository;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.transaction.TotalSumaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceCalculateUseCase {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private IncomeSumaryFindUseCase incomeSumaryFindUseCase;

    @Autowired
    private ExpenseSumaryFindUseCase expenseSumaryFindUseCase;

    @Autowired
    private TransactionFindUseCase transactionFindUseCase;

    public TotalSumaryDto calculateCurrentBalance(){
        List<Account> accountList = repository.all();

        double total_account = 0;
        for(Account account:accountList){
            total_account += account.initial_balance;
        }

        Optional<IncomeSumary> total_income_optional = incomeSumaryFindUseCase.getCurrent();
        Optional<ExpenseSummary> total_expense_optional = expenseSumaryFindUseCase.getCurrent();
        double total_income = 0;
        if(total_income_optional.isPresent()){
            total_income = total_income_optional.get().total_income;
        }
        double total_expense = 0;
        if(total_expense_optional.isPresent()){
            total_expense = total_expense_optional.get().total_expense;
        }

        total_account= total_account + total_income - total_expense;

        return TotalSumaryDto.builder().totalExpense(total_expense).totalIncome(total_income).balance(total_account).build();

    }







}
