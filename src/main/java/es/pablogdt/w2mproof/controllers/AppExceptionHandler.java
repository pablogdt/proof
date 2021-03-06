package es.pablogdt.w2mproof.controllers;

import es.pablogdt.w2mproof.exceptions.SuperheroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SuperheroNotFoundException.class)
    public final ResponseEntity<String> handleSuperheroNotFoundException(SuperheroNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>("Superhero not found", HttpStatus.NOT_FOUND);
    }
}
