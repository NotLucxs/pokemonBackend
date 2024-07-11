package com.spring.learning.pokemon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonNameRepository extends CrudRepository<PokemonNameEntity, String> {
    //
}
