package com.spring.learning;

import com.google.gson.Gson;
import com.spring.learning.pokemon.Pokemon;
import com.spring.learning.pokemon.PokemonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Arrays;


@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) throws FileNotFoundException {
		var context = SpringApplication.run(LearningApplication.class, args);
//		var userService = context.getBean(UserService.class);
//		userService.generateUsers();
//		var users = userService.getAllUserProfiles();
//		System.out.println(users);

		var pokemonService = context.getBean(PokemonService.class);

		var path = Path.of("./pokemon/pokedex.json");
		var gson = new Gson();
		var file = new BufferedReader(new FileReader(path.toAbsolutePath().toString()));
		var json = gson.fromJson(file, Pokemon[].class);
		Arrays.stream(json).iterator().forEachRemaining(pokemonService::savePokemon);

		//pokemonService.getAllPokemon().forEachRemaining(System.out::println);
		var frenchPokemon = pokemonService.getPokemonByName("兰螳花");
		System.out.println(frenchPokemon.getId());
	}

}
