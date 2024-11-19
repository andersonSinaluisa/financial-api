package com.andersonsinaluisa.financial_api.core.application.find;

import com.andersonsinaluisa.financial_api.core.domain.model.Account;
import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountFindUseCase {
    @Autowired
    private  AccountRepository accountRepository = null;

    public Page<Account> findAll(Pageable pageable){
        return this.accountRepository.all(pageable);
    }
    public List<Account> findAll(){
        return this.accountRepository.all();
    }
    public Account findById(Integer id){
        return this.accountRepository.getById(id).orElseThrow();
    }


}
