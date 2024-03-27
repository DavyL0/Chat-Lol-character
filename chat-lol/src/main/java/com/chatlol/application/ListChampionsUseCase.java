package com.chatlol.application;

import com.chatlol.domain.model.Champions;
import com.chatlol.domain.ports.ChampionsRepository;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepository repository) {
  public List<Champions> findAll(){
      return repository.findAll();
  }
}
