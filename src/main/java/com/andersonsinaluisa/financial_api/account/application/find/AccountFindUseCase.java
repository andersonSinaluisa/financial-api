package com.andersonsinaluisa.financial_api.account.application.find;

import com.andersonsinaluisa.financial_api.account.domain.model.Account;
import com.andersonsinaluisa.financial_api.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountFindUseCase {
    private  AccountRepository accountRepository = null;

    public List<Account> findAll(){
        return this.accountRepository.all();
    }
    public Account findById(Integer id){
        return this.accountRepository.getById(id).orElseThrow();
    }


}
