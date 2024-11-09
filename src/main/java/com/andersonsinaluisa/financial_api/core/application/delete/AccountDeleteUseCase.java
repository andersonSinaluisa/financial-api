package com.andersonsinaluisa.financial_api.core.application.delete;


import com.andersonsinaluisa.financial_api.core.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDeleteUseCase {
    @Autowired
    private AccountRepository accountRepository = null;

    public void deleteOne(long id){
        this.accountRepository.deleteById(id);
    }

}
