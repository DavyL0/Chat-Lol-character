package com.chatlol.domain.ports;

import com.chatlol.domain.model.Champions;

import java.util.List;
import java.util.Optional;

public interface ChampionsRepository {

    List<Champions> findAll();

    Optional<Champions>findByID(Long id);
}
