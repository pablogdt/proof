package es.pablogdt.w2mproof.controllers;

import es.pablogdt.w2mproof.model.Superhero;
import es.pablogdt.w2mproof.model.dto.SuperheroDTO;
import es.pablogdt.w2mproof.services.SuperheroOperationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/superheroes")
public class SuperheroController {

    @Autowired
    private SuperheroOperationsService superheroOperationsService;

    @GetMapping(value = "/name/{substring}")
    @Operation(summary = "Find Superheroes by name. It can be used as a substring which will return all superheroes matching it", description = "Name search by %name% format", tags = { "superhero" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Superhero.class)))) })
    public List<Superhero> searchSuperheroByName(@PathVariable String substring) {
        return superheroOperationsService.findSuperheroesByName(substring);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find Superheroes by id.", description = "Search by id", tags = { "superhero" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Superhero.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content(schema = @Schema(type = "string", example = "Superhero not found")))
    })
    public Superhero findSuperheroById(@PathVariable Long id) {
        return superheroOperationsService.findSuperheroById(id);
    }

    @GetMapping
    @Operation(summary = "Find All superheroes.", description = "Search all", tags = { "superhero" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Superhero.class))),
    })
    public List<Superhero> findAllSuperheroes() {
        return superheroOperationsService.findAllSuperheroes();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete superhero by id.", description = "Delete by id", tags = { "superhero" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content(schema = @Schema(type = "string", example = "Superhero not found")))
    })
    public void deleteSuperhero(@PathVariable Long id) {
        superheroOperationsService.deleteSuperhero(id);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Modify superhero.", description = "Modify superhero by the id provided", tags = { "superhero" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Superhero.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content(schema = @Schema(type = "string", example = "Superhero not found")))
    })
    public Superhero modifySuperhero(@PathVariable Long id, @RequestBody SuperheroDTO superhero) {
        return superheroOperationsService.modifySuperhero(id, superhero);
    }
}
