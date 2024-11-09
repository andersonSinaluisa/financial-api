package com.andersonsinaluisa.financial_api.core.application.create;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCreateUseCase {
    @Autowired
    private AccountRepository accountRepository;



    public Account create(Account data){
        Account account = this.accountRepository.create(data).orElseThrow();
        return account;
    }


}
