package es.pablogdt.w2mproof.services;

import com.google.common.collect.Lists;
import es.pablogdt.w2mproof.annotations.Chronometer;
import es.pablogdt.w2mproof.exceptions.SuperheroNotFoundException;
import es.pablogdt.w2mproof.model.Superhero;
import es.pablogdt.w2mproof.model.Superpower;
import es.pablogdt.w2mproof.model.dto.SuperheroDTO;
import es.pablogdt.w2mproof.model.dto.SuperpowerDTO;
import es.pablogdt.w2mproof.repository.SuperheroRepository;
import es.pablogdt.w2mproof.repository.SuperpowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuperheroOperationsService {

    @Autowired
    private SuperheroRepository superheroRepository;

    @Autowired
    private SuperpowerRepository superpowerRepository;

    @Chronometer
    public List<Superhero> findSuperheroesByName(String substring) {
        return superheroRepository.findByNameContaining(substring);
    }

    @Chronometer
    @Cacheable(cacheNames = "superheroes")
    public Superhero findSuperheroById(Long id) {
        Optional<Superhero> optional = superheroRepository.findById(id);
        if (optional.isEmpty()) {
            throw new SuperheroNotFoundException();
        }
        return optional.get();
    }

    @Chronometer
    @Cacheable(cacheNames = "allSuperheroes")
    public List<Superhero> findAllSuperheroes() {
        return Lists.newArrayList(superheroRepository.findAll());
    }

    @Chronometer
    public Superhero modifySuperhero(Long id, SuperheroDTO superhero) {
        Optional<Superhero> optional = superheroRepository.findById(id);
        if (optional.isEmpty()) {
            throw new SuperheroNotFoundException();
        } else {
            Superhero saved = optional.get();
            if (superhero.getName() != null) {
                saved.setName(superhero.getName());
            }
            if (superhero.getSuperpowers() != null) {
                List<Superpower> superpowers = new ArrayList<>();
                for (SuperpowerDTO superpower : superhero.getSuperpowers()) {
                    Optional<Superpower> optionalSuperpower = superpowerRepository.findById(superpower.getId());
                    Superpower superpowerSaved;
                    if (optionalSuperpower.isPresent()) {
                        superpowerSaved = optionalSuperpower.get();
                        superpowers.add(superpowerSaved);
                    }
                }
                saved.setSuperpowers(superpowers);
            }
            return superheroRepository.save(saved);
        }
    }

    @Chronometer
    public void deleteSuperhero(Long id) {
        try {
            superheroRepository.deleteById(id);
        } catch (Exception exception) {
            throw new SuperheroNotFoundException();
        }
    }
}
