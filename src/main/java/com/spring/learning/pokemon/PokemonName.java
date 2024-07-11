package com.spring.learning.pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonName implements Serializable {
    public String english;
    public String japanese;
    public String chinese;
    public String french;
}
