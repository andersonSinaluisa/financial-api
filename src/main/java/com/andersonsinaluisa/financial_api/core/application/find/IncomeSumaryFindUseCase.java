package com.andersonsinaluisa.financial_api.core.application.find;

import com.andersonsinaluisa.financial_api.core.domain.model.IncomeSumary;
import com.andersonsinaluisa.financial_api.core.domain.repository.IncomeSumaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncomeSumaryFindUseCase {

    @Autowired
    private final IncomeSumaryRepository incomeSumaryRepository;

    public Flux<IncomeSumary> all(){
        return incomeSumaryRepository.all();
    }

    public Mono<IncomeSumary> getById(long id){
        return  incomeSumaryRepository.getById(id);
    }

    public Mono<IncomeSumary> getCurrent(){
        return incomeSumaryRepository.getLast();
    }


}
