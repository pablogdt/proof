package es.pablogdt.w2mproof.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "superpowers")
public class Superpower implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    @Schema(description = "Power power name", example = "Super strength")
    private String name;

    @NotBlank
    @NotNull
    private String description;

}
