package es.pablogdt.w2mproof.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "superheroes")
public class Superhero implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @NotNull
    private String name;

    @ManyToMany
    @NotNull
    @Size(min = 1)
    private List<Superpower> superpowers;
}
