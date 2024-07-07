package com.spring.learning.pokemon;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class PokemonName {
    private String english;
    private String japanese;
    private String chinese;
    private String french;
}
