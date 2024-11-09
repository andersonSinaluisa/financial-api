package com.andersonsinaluisa.financial_api.core.application.delete;

import com.andersonsinaluisa.financial_api.core.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionDeleteUseCase {
    @Autowired
    public final TransactionRepository repository;

    public void delete(long id){
         this.repository.deleteById(id);
    }

}
