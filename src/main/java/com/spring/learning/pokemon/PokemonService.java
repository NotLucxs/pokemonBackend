package com.spring.learning.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;

@Service
public class PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    PokemonNameRepository pokemonNameRepository;

    public Pokemon getPokemonById(Integer id) {
        return pokemonRepository.findById(id).orElseThrow(() -> new PokemonException("Unable to find Pokemon with ID: "+id));
    }

    public Pokemon getPokemonByName(String pokemonName) {
        PokemonNameEntity pokemonNameEntity = pokemonNameRepository.findById(pokemonName).orElseThrow(() -> new PokemonException("Unable to find Pokemon with name: "+pokemonName));
        return pokemonRepository.findById(pokemonNameEntity.getId()).orElseThrow(() -> new PokemonException("Unable to find Pokemon with ID: "+pokemonNameEntity.getId()));
    }

    public void savePokemon(Pokemon pokemon) {
        pokemonRepository.save(pokemon);
        var names = pokemon.getName();
        Arrays.stream(names.getClass().getDeclaredFields()).iterator()
                .forEachRemaining(language -> {
                            try {
                                Field field = names.getClass().getDeclaredField(language.getName());
                                pokemonNameRepository.save(PokemonNameEntity.builder()
                                        .name(field.get(names).toString())
                                        .id(pokemon.getId())
                                        .build());
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                throw new PokemonException("Unable to save Pokemon: "+pokemon.getId(), e);
                            }
                        }
                );

    }

    public void deletePokemon(Pokemon pokemon) {
        pokemonRepository.delete(pokemon);
    }

    public Iterator<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll().iterator();
    }

}
