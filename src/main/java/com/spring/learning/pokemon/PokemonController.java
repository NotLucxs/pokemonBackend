package com.spring.learning.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
