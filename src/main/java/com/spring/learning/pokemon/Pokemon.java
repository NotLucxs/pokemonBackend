package com.spring.learning.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@SuperBuilder
@Data
public class Pokemon {
    private int id;
    private PokemonName name;
    private ArrayList<String> type;
    @JsonProperty("base")
    private PokemonBase base;
}
