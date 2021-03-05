package es.pablogdt.w2mproof.services;

import com.google.common.collect.Lists;
import es.pablogdt.w2mproof.model.Superhero;
import es.pablogdt.w2mproof.repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SuperheroOperationsService {

    @Autowired
    private SuperheroRepository superheroRepository;

    public List<Superhero> findSuperherosByName(String substring) {
        return superheroRepository.findByNameContaining(substring);
    }

    public Superhero findSuperheroById(Long id) {
        return superheroRepository.findById(id).orElse(null);
    }

    public List<Superhero> findAllSuperheros() {
        return Lists.newArrayList(superheroRepository.findAll());
    }

    public Superhero modifySuperhero(Long id, Superhero superhero) {
        Superhero saved = superheroRepository.findById(id).orElse(null);
        if (saved != null) {
            saved.setName(superhero.getName());
            superheroRepository.save(saved);
        }
        return saved;
    }

    public void deleteSuperhero(Long id) {
        superheroRepository.deleteById(id);
    }
}
