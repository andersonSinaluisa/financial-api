package com.andersonsinaluisa.financial_api.core.domain.repository;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Mono<Optional<Account>> create(Account data);
    Mono<Optional<Account>> update(Account data);
    Mono<Optional<Account>> getById(long id);
    Mono<Page<Account>> all(Pageable pageable);
    Mono<Page<Account>> all(String search, Pageable pageable);
    Flux<Account> all();
    Mono<Void> deleteById(long ig);
    Mono<Account> getBySlug(String slug);

}
