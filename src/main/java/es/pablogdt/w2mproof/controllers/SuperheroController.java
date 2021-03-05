package es.pablogdt.w2mproof.controllers;

import es.pablogdt.w2mproof.model.Superhero;
import es.pablogdt.w2mproof.services.SuperheroOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/superheroes")
public class SuperheroController {

    @Autowired
    private SuperheroOperationsService superheroOperationsService;

    @GetMapping(value = "/name/{substring}")
    public List<Superhero> searchSuperheroByName(@PathVariable String substring) {
        return superheroOperationsService.findSuperheroesByName(substring);
    }

    @GetMapping(value = "/{id}")
    public Superhero findSuperheroById(@PathVariable Long id) {
        return superheroOperationsService.findSuperheroById(id);
    }

    @GetMapping
    public List<Superhero> findAllSuperheroes() {
        return superheroOperationsService.findAllSuperheroes();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSuperhero(@PathVariable Long id) {
        superheroOperationsService.deleteSuperhero(id);
    }

    @PutMapping(value = "/{id}")
    public Superhero modifySuperhero(@PathVariable Long id, @RequestBody Superhero superhero) {
        return superheroOperationsService.modifySuperhero(id, superhero);
    }
}
