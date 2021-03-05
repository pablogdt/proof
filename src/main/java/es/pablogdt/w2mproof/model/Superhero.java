package es.pablogdt.w2mproof.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "superheroes")
public class Superhero implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;
}
