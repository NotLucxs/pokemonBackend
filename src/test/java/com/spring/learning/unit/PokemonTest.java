package com.spring.learning.unit;

import com.google.gson.Gson;
import com.spring.learning.pokemon.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = PokemonController.class)
@AutoConfigureMockMvc(addFilters = false)   // Set this so you don't need to pass JWT tokens
@ExtendWith(MockitoExtension.class)
public class PokemonTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PokemonService pokemonService;

    private Pokemon pokemon;
    private Gson gson;

    @BeforeEach
    void init() {
        var typeList = new ArrayList<String>();
        typeList.add("Grass");
        typeList.add("Poison");
        PokemonBase stats = PokemonBase.builder().hp(45).attack(49).defense(49).specialAttack(65).specialDefense(65).speed(45).build();
        pokemon = Pokemon.builder().id(1).name("Bulbasaur").type(typeList).base(stats).build();

        gson = new Gson();
    }

    @Test
    void testGetPokemonById() throws Exception {
        given(pokemonService.getPokemonById(ArgumentMatchers.anyInt())).willReturn(pokemon);
        MvcResult response = mockMvc.perform(get("/pokemon/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = response.getResponse().getContentAsString();
        Pokemon returnedPokemon = gson.fromJson(content, Pokemon.class);

        Assertions.assertEquals(1, returnedPokemon.getId());
        Assertions.assertEquals("Bulbasaur", returnedPokemon.getName());
        Assertions.assertEquals("Grass", returnedPokemon.getType().get(0));
        Assertions.assertEquals("Poison", returnedPokemon.getType().get(1));
    }
}
