package es.pablogdt.w2mproof;

import es.pablogdt.w2mproof.model.Superhero;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
class W2mproofApplicationTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private TestRestTemplate template;

	@Test
	void getSuperheroTest() {
		ResponseEntity<Superhero> result = template.withBasicAuth("user", "31743707-684e-4cba-8188-643e0237a7c9")
				.getForEntity("/superheroes/0", Superhero.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
		assertEquals("Superman", result.getBody().getName());
		assertEquals(0L, result.getBody().getId().longValue());
	}

	@Test
	void findSuperheroByNameTest() {
		ResponseEntity<Superhero[]> result = template.withBasicAuth("user", "31743707-684e-4cba-8188-643e0237a7c9")
				.getForEntity("/superheroes/name/man", Superhero[].class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
		assertEquals(3, result.getBody().length);
		for (Superhero superhero : result.getBody()) {
			assertTrue(superhero.getName().contains("man"));
		}
	}

	@Test
	void getAllSuperheroesTest() {
		ResponseEntity<Superhero[]> result = template.withBasicAuth("user", "31743707-684e-4cba-8188-643e0237a7c9")
				.getForEntity("/superheroes/", Superhero[].class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
		assertEquals(5, result.getBody().length);
	}

	@Test
	void deleteSuperheroTest() {
		template.withBasicAuth("user", "31743707-684e-4cba-8188-643e0237a7c9")
				.delete("/superheroes/0");
		ResponseEntity<String> resultCheck = template.withBasicAuth("user", "31743707-684e-4cba-8188-643e0237a7c9")
				.getForEntity("/superheroes/0", String.class);
		assertEquals(HttpStatus.NOT_FOUND, resultCheck.getStatusCode());
		assertEquals("Superhero not found", resultCheck.getBody());
	}

	@Test
	void modifySuperheroTest() {
		Superhero superhero = new Superhero();
		superhero.setName("Deadpool");
		template.withBasicAuth("user", "31743707-684e-4cba-8188-643e0237a7c9")
				.put("/superheroes/0", superhero);
		ResponseEntity<Superhero> resultCheck = template.withBasicAuth("user", "31743707-684e-4cba-8188-643e0237a7c9")
				.getForEntity("/superheroes/0", Superhero.class);
		assertEquals(HttpStatus.OK, resultCheck.getStatusCode());
		assertNotNull(resultCheck.getBody());
		assertEquals("Deadpool", resultCheck.getBody().getName());
	}
}
