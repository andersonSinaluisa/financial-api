package com.andersonsinaluisa.financial_api.core.application.update;

import com.andersonsinaluisa.financial_api.core.domain.exception.AccountNotFoundException;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountUpdateUseCase {

    private final AccountRepository repository;

    public Mono<Account> update(Account data) {
        return repository.update(data)
                .map(Optional::get)
                .switchIfEmpty(Mono.error(new AccountNotFoundException("Cuenta no encontrada"))); // Manejo del caso de ausencia de cuenta
    }

}
