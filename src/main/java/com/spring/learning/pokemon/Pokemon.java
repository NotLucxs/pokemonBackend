package com.spring.learning.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@SuperBuilder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    @Id
    private Integer id;
    private PokemonName name;
    private ArrayList<String> type;
    @JsonProperty("base")
    private PokemonBase base;
}
