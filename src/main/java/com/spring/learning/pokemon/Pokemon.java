package com.spring.learning.pokemon;

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
    private String name;
    private ArrayList<String> type;
    private PokemonBase base;
}
