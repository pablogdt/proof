package es.pablogdt.w2mproof.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "superpowers")
public class Superpower implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

}
