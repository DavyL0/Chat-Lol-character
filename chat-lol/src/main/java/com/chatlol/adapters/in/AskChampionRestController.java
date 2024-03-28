package com.chatlol.adapters.in;

import com.chatlol.application.AskChampionUseCase;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/champions")
public record AskChampionRestController(AskChampionUseCase useCase) {
    @CrossOrigin
    @PostMapping("/{championId}/ask")
    public com.chatlol.adapters.in.AskChampionRestController.AskChampionResponse askChampion(@PathVariable Long championId, @RequestBody com.chatlol.adapters.in.AskChampionRestController.AskChampionRequest request) {
        String answer = useCase.askChampion(championId, request.question());
        return new com.chatlol.adapters.in.AskChampionRestController.AskChampionResponse(answer);
    }

    public record AskChampionRequest(String question) { }
    public record AskChampionResponse(String answer) { }
}


