package es.pablogdt.w2mproof.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SuperpowerDTO implements Serializable {
    @Id
    private Long id;

    @NotBlank
    @NotNull
    @Schema(description = "Super power name", example = "Super strength")
    private String name;

    @NotBlank
    @NotNull
    private String description;

}
