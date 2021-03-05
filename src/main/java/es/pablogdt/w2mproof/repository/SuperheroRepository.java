package es.pablogdt.w2mproof.repository;

import es.pablogdt.w2mproof.model.Superhero;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SuperheroRepository extends CrudRepository<Superhero, Long> {
    List<Superhero> findByNameContaining(String substring);
}
