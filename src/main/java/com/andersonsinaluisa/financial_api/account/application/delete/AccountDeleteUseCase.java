package com.andersonsinaluisa.financial_api.account.application.delete;


import com.andersonsinaluisa.financial_api.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDeleteUseCase {
    private AccountRepository accountRepository = null;

    public void deleteOne(int id){
        this.accountRepository.deleteById(id);
    }

}
