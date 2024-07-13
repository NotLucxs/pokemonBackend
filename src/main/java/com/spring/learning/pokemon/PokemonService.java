package com.spring.learning.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    public Pokemon getPokemonById(Integer id) {
        return pokemonRepository.findById(id).orElseThrow(() -> new PokemonException("Unable to find Pokemon with ID: "+id));
    }

    public Pokemon getPokemonByName(String pokemonName) {
        return pokemonRepository.findByName(pokemonName).orElseThrow(() -> new PokemonException("Unable to find Pokemon with Name: "+pokemonName));
    }

    public void savePokemon(Pokemon pokemon) {
        pokemonRepository.save(pokemon);
    }

    public void deletePokemon(Pokemon pokemon) {
        pokemonRepository.delete(pokemon);
    }

    public Iterator<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll().iterator();
    }

}
