package com.spring.learning.pokemon;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonBase implements Serializable {
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