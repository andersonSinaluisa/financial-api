package com.andersonsinaluisa.financial_api.core.application.create;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeSumaryCreateUseCase {

    @Autowired
    private final IncomeSumaryRepository incomeSumaryRepository;


    public IncomeSumary create(IncomeSumary data){
        return incomeSumaryRepository.create(data).orElseThrow();
    }



}
