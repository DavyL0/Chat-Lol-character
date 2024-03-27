package com.chatlol.application;

import com.chatlol.domain.exception.ChaompionNotFoundException;
import com.chatlol.domain.model.Champions;
import com.chatlol.domain.ports.ChampionsRepository;

public record AskChampionUseCase(ChampionsRepository repository) {
    public String askChampion(Long championId, String question){

       Champions champion = repository.findByID(championId)
               .orElseThrow(()-> new ChaompionNotFoundException(championId));

       //evoluir a lógica para considerar a integração de IA
        return champion.generateContextByQuestion(question);
    }
}
