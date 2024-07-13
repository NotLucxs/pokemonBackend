package com.spring.learning.pokemon;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PokemonDeserializer implements JsonDeserializer<Pokemon> {

    @Override
    public Pokemon deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        // Get Pokemon ID
        Integer id = jsonObject.get("id").getAsInt();
        // Get Pokemon English name
        JsonObject nameObject = jsonObject.getAsJsonObject("name");
        String englishName = nameObject.get("english").getAsString();
        // Get Pokemon types
        JsonArray typeArray = jsonObject.getAsJsonArray("type");
        ArrayList<String> types = new ArrayList<>();
        typeArray.iterator().forEachRemaining(element -> types.add(element.getAsString()));
        // Get Pokemon stats
        JsonObject statsObject = jsonObject.getAsJsonObject("base");
        PokemonBase stats = PokemonBase.builder()
                .hp(statsObject.get("HP").getAsInt())
                .attack(statsObject.get("Attack").getAsInt())
                .defense(statsObject.get("Defense").getAsInt())
                .specialAttack(statsObject.get("Sp. Attack").getAsInt())
                .specialDefense(statsObject.get("Sp. Defense").getAsInt())
                .speed(statsObject.get("Speed").getAsInt())
                .build();

        return Pokemon.builder().id(id).name(englishName).type(types).base(stats).build();
    }
}
