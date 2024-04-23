package RVABackendTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import RVA.models.Film;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FilmControllerIntegrationTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilmControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Film>> response = template.exchange(
				"/film", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Film>>() {});
		
		List<Film> list = response.getBody();
		for(int i=0; i < list.size(); i++) {
			if(highestId <= list.get(i).getId()){
				highestId = list.get(i).getId() + 1;
			}
		}
	}
	
	void getHighestId() {
		createHighestId();
		highestId--;
	}
	
	@Test
	@Order(1)
	void TestGetAllFilms() {
		ResponseEntity<List<Film>> response = template.exchange(
				"/film", HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Film>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Film> filmovi = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!filmovi.isEmpty());
	}
	
	@Test
	@Order(2)
	void testGetFilmsById() {
		int id = 1;
		ResponseEntity<Film> response = template.exchange(
				"/film/id/" + id, HttpMethod.GET, null, Film.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertEquals(id, response.getBody().getId());
	
	}
	
	@Test
	@Order(3)
	void testGetFilmsByNaziv() {
		String naziv = "The";
		ResponseEntity<List<Film>> response = template.exchange(
				"/film/naziv/" + naziv, HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Film>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Film> filmovi = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(filmovi.get(0));
		for(Film a : filmovi) {
			assertTrue(a.getNaziv().contains(naziv));

			// U slucaju brojcane vrednosti
			// assertTrue(a.getVrednost() < predefinisanaVrednost)
			// ILI
			// assertTrue(a.getVrednost() > predefinisanaVrednost)
			
			//U slucaju boolean vrednosti
			// assertTrue(a.getBooleanVrednost());
		}
	}
	
	@Test
	@Order(4)
	void testCreateFilm() {
		Film film = new Film();
		film.setNaziv("POST naziv");
		film.setZanr("POST zanr");
		film.setRecenzija(7);
		film.setTrajanje(120);
		
		HttpEntity<Film> entity = new HttpEntity<Film>(film);
		createHighestId();
		
		ResponseEntity<Film> response = template.exchange(
				"/film", HttpMethod.POST, entity, Film.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(201, statusCode);
		assertEquals("/film/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(film.getNaziv(), response.getBody().getNaziv());
		assertEquals(film.getZanr(), response.getBody().getZanr());	
		assertEquals(film.getRecenzija(), response.getBody().getRecenzija());	
		assertEquals(film.getTrajanje(), response.getBody().getTrajanje());	
	}
	

	@Test
	@Order(5)
	void TestUpdateFilm() {
		Film film = new Film();
		film.setNaziv("PUT naziv");
		film.setZanr("PUT zanr");
		film.setRecenzija(77);
		film.setTrajanje(220);	
		
		HttpEntity<Film> entity = new HttpEntity<Film>(film);
		getHighestId();
		
		ResponseEntity<Film> response = template.exchange(
				"/film/id/" + highestId, HttpMethod.PUT, entity, Film.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof Film);
		assertEquals(film.getNaziv(), response.getBody().getNaziv());
		assertEquals(film.getRecenzija(), response.getBody().getRecenzija());	
		assertEquals(film.getTrajanje(), response.getBody().getTrajanje());	
		assertEquals(film.getZanr(), response.getBody().getZanr());	
	}
	
	@Test
	@Order(6)
	void TestDeleteFilmById() {
		getHighestId();
		ResponseEntity<String> response = template.exchange(
				"/film/id/" + highestId, HttpMethod.DELETE, null, String.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("has been deleted."));
				
	}
}