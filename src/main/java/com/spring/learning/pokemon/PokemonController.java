package com.spring.learning.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping("/pokemon/{id}")
    @ResponseBody
    public Pokemon getPokemonById(@PathVariable Integer id) {
        return pokemonService.getPokemonById(id);
    }

    @GetMapping("/pokemon")
    @ResponseBody
    public Pokemon getPokemonByName(@RequestParam(value="name") String name) {
        return pokemonService.getPokemonByName(name);
    }

}
