package es.pablogdt.w2mproof.services;

import com.google.common.collect.Lists;
import es.pablogdt.w2mproof.annotations.Chronometer;
import es.pablogdt.w2mproof.model.Superhero;
import es.pablogdt.w2mproof.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SuperheroOperationsService {

    @Autowired
    private SuperheroRepository superheroRepository;

    @Chronometer
    public List<Superhero> findSuperheroesByName(String substring) {
        return superheroRepository.findByNameContaining(substring);
    }

    @Chronometer
    @Cacheable(cacheNames = "superheroes")
    public Superhero findSuperheroById(Long id) {
        return superheroRepository.findById(id).orElse(null);
    }

    @Chronometer
    @Cacheable(cacheNames = "allSuperheroes")
    public List<Superhero> findAllSuperheroes() {
        return Lists.newArrayList(superheroRepository.findAll());
    }

    @Chronometer
    public Superhero modifySuperhero(Long id, Superhero superhero) {
        Superhero saved = superheroRepository.findById(id).orElse(null);
        if (saved != null) {
            saved.setName(superhero.getName());
            superheroRepository.save(saved);
        }
        return saved;
    }

    @Chronometer
    public void deleteSuperhero(Long id) {
        superheroRepository.deleteById(id);
    }
}
