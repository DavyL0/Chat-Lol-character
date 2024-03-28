package com.chatlol.application;

import com.chatlol.domain.exception.ChaompionNotFoundException;
import com.chatlol.domain.model.Champions;
import com.chatlol.domain.ports.ChampionsRepository;
import com.chatlol.domain.ports.GenerativeAiApi;

public record AskChampionUseCase(ChampionsRepository repository, GenerativeAiApi genAiApi) {
    public String askChampion(Long championId, String question){

       Champions champion = repository.findByID(championId)
               .orElseThrow(()-> new ChaompionNotFoundException(championId));

       String context = champion.generateContextByQuestion(question);
       String objective = """
               Atue como uma assistente com a habilidade de se comportar como os Campeões do League of Legends (LOL)
               Responda perguntas incorporando a personalidade e estilo de um determinado Campeão.
               Segue a pergunta, o nome do Campeão e sua respectiva lore (história):
               """;
        return genAiApi.generateContent(objective, context);
    }
}
