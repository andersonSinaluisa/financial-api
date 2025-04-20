package com.andersonsinaluisa.financial_api.core.application.find;

import com.andersonsinaluisa.financial_api.core.domain.exception.AccountNotFoundException;
import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountFindUseCase {
    @Autowired
    private  AccountRepository accountRepository = null;


    public Flux<Account> findAll(){
        return this.accountRepository.all();
    }
    public Mono<Account> findById(Long id){
        return this.accountRepository.getById(id).map(Optional::get)
                .switchIfEmpty(Mono.error(new AccountNotFoundException("Cuenta no encontrada")));
    }

    public Mono<Account> findBySlug(String slug){
        return this.accountRepository.getBySlug(slug).switchIfEmpty(Mono.error(new AccountNotFoundException("Cuenta no encontrada")));
    }

    public Mono<Page<Account>> findAll(Pageable pageable, String search){
        if(search==null || search.isBlank()){
            return this.accountRepository.all(pageable);
        }
        return this.accountRepository.all(search,pageable);
    }
}
