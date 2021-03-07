package es.pablogdt.w2mproof.services;

import com.google.common.collect.Lists;
import es.pablogdt.w2mproof.exceptions.SuperheroNotFoundException;
import es.pablogdt.w2mproof.model.Superhero;
import es.pablogdt.w2mproof.model.dto.SuperheroDTO;
import es.pablogdt.w2mproof.repository.SuperheroRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SuperheroOperationsServiceTest {

    @InjectMocks
    public SuperheroOperationsService superheroOperationsService;

    @Mock
    private SuperheroRepository superheroRepository;

    @Test
    public void findSuperheroesByName() {
        Superhero superhero1 = new Superhero();
        superhero1.setName("Antman");
        Superhero superhero2 = new Superhero();
        superhero2.setName("Aquaman");
        when(superheroRepository.findByNameContaining(isA(String.class)))
                .thenReturn(Lists.newArrayList(superhero1, superhero2));
        List<Superhero> superheroList = superheroOperationsService.findSuperheroesByName("man");
        assertEquals(2, superheroList.size());
        assertEquals("Antman", superheroList.get(0).getName());
        assertEquals("Aquaman", superheroList.get(1).getName());
    }

    @Test
    public void findSuperheroById() {
        when(superheroRepository.findById(isA(Long.class)))
                .thenReturn(Optional.of(new Superhero(0L, "Wonderwoman", null)));
        Superhero superhero = superheroOperationsService.findSuperheroById(0L);
        assertEquals("Wonderwoman", superhero.getName());
        assertEquals(0L, superhero.getId().longValue());
    }

    @Test(expected = SuperheroNotFoundException.class)
    public void findSuperheroByIdNotFound() {
        when(superheroRepository.findById(isA(Long.class))).thenReturn(Optional.empty());
        superheroOperationsService.findSuperheroById(0L);
    }

    @Test
    public void findAllSuperheroes() {
        Superhero superhero1 = new Superhero();
        superhero1.setName("Storm");
        Superhero superhero2 = new Superhero();
        superhero2.setName("Black Widow");
        Superhero superhero3 = new Superhero();
        superhero3.setName("Jean Gray");
        when(superheroRepository.findAll()).thenReturn(Lists.newArrayList(superhero1, superhero2, superhero3));
        List<Superhero> superheroList = superheroOperationsService.findAllSuperheroes();
        assertEquals(3, superheroList.size());
        assertEquals("Storm", superheroList.get(0).getName());
        assertEquals("Black Widow", superheroList.get(1).getName());
        assertEquals("Jean Gray", superheroList.get(2).getName());
    }

    @Test
    public void modifySuperhero() {
        Superhero superhero1 = new Superhero();
        superhero1.setId(0L);
        superhero1.setName("Storm");
        SuperheroDTO modifiedSuperhero = new SuperheroDTO();
        modifiedSuperhero.setName("Batman");
        when(superheroRepository.findById(isA(Long.class))).thenReturn(Optional.of(superhero1));
        when(superheroRepository.save(isA(Superhero.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return invocation.getArguments()[0];
            }
        });
        Superhero superhero = superheroOperationsService.modifySuperhero(0L, modifiedSuperhero);
        assertEquals("Batman", superhero.getName());
    }

    @Test(expected = SuperheroNotFoundException.class)
    public void modifySuperheroNotFound() {
        Superhero superhero1 = new Superhero();
        superhero1.setId(0L);
        superhero1.setName("Storm");
        SuperheroDTO modifiedSuperhero = new SuperheroDTO();
        modifiedSuperhero.setName("Batman");
        when(superheroRepository.findById(isA(Long.class))).thenReturn(Optional.empty());
        superheroOperationsService.modifySuperhero(0L, modifiedSuperhero);
    }

    @Test
    public void deleteSuperhero() {
        superheroOperationsService.deleteSuperhero(0L);
        verify(superheroRepository, times(1)).deleteById(0L);
    }

    @Test(expected = SuperheroNotFoundException.class)
    public void deleteSuperheroNotFound() {
        doThrow(EmptyResultDataAccessException.class).when(superheroRepository).deleteById(100L);
        superheroOperationsService.deleteSuperhero(100L);
    }
}