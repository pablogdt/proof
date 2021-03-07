package es.pablogdt.w2mproof.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperheroDTO implements Serializable {
    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    @Size(min = 1)
    private List<SuperpowerDTO> superpowers;
}
