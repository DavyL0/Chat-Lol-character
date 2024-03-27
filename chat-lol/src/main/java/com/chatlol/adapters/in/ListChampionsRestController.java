package com.chatlol.adapters.in;

import com.chatlol.application.ListChampionsUseCase;
import com.chatlol.domain.model.Champions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {
    @GetMapping
    public List<Champions> findAllChampions(){
        return useCase.findAll();

}}
