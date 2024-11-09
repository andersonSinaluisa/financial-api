package com.andersonsinaluisa.financial_api.core.application.update;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountUpdateUseCase {

    private final AccountRepository repository;

    public Account update(Account data){
        return repository.update(data).orElseThrow();
    }

}
