package com.andersonsinaluisa.financial_api.core.application.create;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountCreateUseCase {
    @Autowired
    private AccountRepository accountRepository;



    public Mono<Account> create(Account data) {

        data.slug = Slugify.builder().build().slugify(data.account_number.concat(data.account_name));
        data.current_balance = data.initial_balance;

        return this.accountRepository.create(data)
                .flatMap(optionalAccount -> optionalAccount
                        .map(Mono::just)
                        .orElseGet(Mono::empty));
    }



}
