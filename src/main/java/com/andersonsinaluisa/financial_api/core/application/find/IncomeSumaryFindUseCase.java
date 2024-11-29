package com.andersonsinaluisa.financial_api.core.application.find;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncomeSumaryFindUseCase {

    @Autowired
    private final IncomeSumaryRepository incomeSumaryRepository;

    public List<IncomeSumary> all(){
        return incomeSumaryRepository.all();
    }

    public IncomeSumary getById(long id){
        return  incomeSumaryRepository.getById(id).orElseThrow();
    }

    public Optional<IncomeSumary> getCurrent(){
        return incomeSumaryRepository.getLast();
    }
}
