package com.andersonsinaluisa.financial_api.account.application.create;

import com.andersonsinaluisa.financial_api.account.domain.model.Account;
import com.andersonsinaluisa.financial_api.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCreateUseCase {
    private AccountRepository accountRepository = null;

    public Account create(Account data){
        return this.accountRepository.create(data).orElseThrow();
    }
}
