package com.chatlol.application;

import com.chatlol.domain.model.Champions;
import com.chatlol.domain.ports.ChampionsRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepository repository) {
  public List<Champions> findAll(){
      return repository.findAll();
  }
}
