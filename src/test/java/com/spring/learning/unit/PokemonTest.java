package com.spring.learning.unit;

import com.spring.learning.pokemon.Pokemon;
import com.spring.learning.storage.LocalStorageSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PokemonTest {

    LocalStorageSystem localStorageSystem = new LocalStorageSystem();

    @Test
    void testGetPokemonById() {
        Pokemon pokemon = localStorageSystem.getPokemonDataById("1");
        System.out.println(pokemon.getBase());
        Assertions.assertEquals(1, pokemon.getId());
        Assertions.assertEquals("Bulbasaur", pokemon.getName().getEnglish());
        Assertions.assertEquals("Grass", pokemon.getType().get(0));
        Assertions.assertEquals("Poison", pokemon.getType().get(1));
        Assertions.assertEquals(1, pokemon.getId());
        Assertions.assertEquals(1, pokemon.getId());
    }
}
