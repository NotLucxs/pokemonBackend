package com.spring.learning.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class PokemonBase {
    @SerializedName("HP")
    private int hp;
    @SerializedName("Attack")
    private int attack;
    @SerializedName("Defense")
    private int defense;
    @SerializedName("Sp. Attack")
    private int specialAttack;
    @SerializedName("Sp. Defense")
    private int specialDefense;
    @SerializedName("Speed")
    private int speed;
}