package RVABackendTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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

import RVA.models.Rezervacija;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RezervacijaControllerIntegrationTest {
	
	@Autowired
	TestRestTemplate template;

	long largestId;

	public void createHighestId() {
		ResponseEntity<List<Rezervacija>> response = template.exchange("/rezervacija", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rezervacija>>() {
				});
		ArrayList<Rezervacija> list = (ArrayList<Rezervacija>) response.getBody();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (largestId <= list.get(i).getId()) {
				largestId = list.get(i).getId() + 1;
			}
		}

	}

	private void getHighestId() {
		createHighestId();
		largestId--;
	}

	@Test
	@Order(1)
	void testGetAllRezervacija() {
		ResponseEntity<List<Rezervacija>> response = template.exchange("/rezervacija", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rezervacija>>() {
				});
		int statusCode = response.getStatusCode().value();
		List<Rezervacija> rezervacija = response.getBody();

		assertEquals(200, statusCode);
		assertNotNull(rezervacija);
	}

	@Test
	@Order(2)
	void testFindRezervacijaById() {
		long id = 1;
		ResponseEntity<Rezervacija> response = template.getForEntity("/rezervacija/id/" + id, Rezervacija.class);
		int statusCode = response.getStatusCode().value();
		Rezervacija rezervacija = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(rezervacija);
		assertEquals(id, rezervacija.getId());
	}
	
	@Test
	@Order(3)
	void testFindStavkaPorudzbineByCenaLessThan() {
		double cena = 400;
		ResponseEntity<List<Rezervacija>> response = template.exchange("/rezervacija/cena/" + cena, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rezervacija>>(){});
		int statusCode = response.getStatusCode().value();
		List<Rezervacija> rezervacija =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(rezervacija.get(0));
		for(Rezervacija p: rezervacija) {
			assertTrue(p.getCena() < cena);
		}
	}

	@Test
	@Order(4)
	void testGetRezervacijasBySala() {
		long porudzbinaId = 1;
		ResponseEntity<List<Rezervacija>> response = template.exchange("/rezervacija/sala/" + porudzbinaId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rezervacija>>(){});
		int statusCode = response.getStatusCode().value();
		List<Rezervacija> rezervacija =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(rezervacija.get(0));
		for(Rezervacija r: rezervacija) {
			assertTrue(r.getSala().getId() == 1);
		}
	}
	
	@Test
	@Order(5)
	void testGetRezervacijaByFilm() {
		long filmId = 1;
		ResponseEntity<List<Rezervacija>> response = template.exchange("/rezervacija/film/" + filmId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rezervacija>>(){});
		int statusCode = response.getStatusCode().value();
		List<Rezervacija> rezervacija =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(rezervacija.get(0));
		for(Rezervacija r: rezervacija) {
			assertTrue(r.getFilm().getId() == 1);
		}
	}

	@Test
	@Order(6)
	void testCreateRezervacija() {
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setCena(1000);
		rezervacija.setBroj_osoba(2);
		Date datum = new Date();
		rezervacija.setDatum(datum);
		rezervacija.setFilm(null);
		rezervacija.setPlaceno(false);
		rezervacija.setSala(null);
		
		HttpEntity<Rezervacija> entity = new HttpEntity<Rezervacija>(rezervacija);
		createHighestId();
		ResponseEntity<Rezervacija> response  = template.postForEntity("/rezervacija", entity, Rezervacija.class);		
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(201, response.getStatusCode().value());
		assertEquals(rezervacija.getCena(), response.getBody().getCena());
		assertEquals(rezervacija.getBroj_osoba(), response.getBody().getBroj_osoba());
		assertEquals(rezervacija.getDatum(), response.getBody().getDatum());
		assertEquals(rezervacija.getFilm(), response.getBody().getFilm());
		assertEquals(rezervacija.getSala(), response.getBody().getSala());
		assertFalse(response.getBody().isPlaceno());
	}

	@Test
	@Order(7)
	void testUpdateRezervacije() {
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setCena(2000);
		rezervacija.setBroj_osoba(17);
		rezervacija.setPlaceno(true);
		
		HttpEntity<Rezervacija> entity = new HttpEntity<Rezervacija>(rezervacija);
		getHighestId();
		ResponseEntity<Rezervacija> response  = template.exchange("/rezervacija/id/" + largestId, HttpMethod.PUT, entity, Rezervacija.class);
		
		assertTrue(response.hasBody());
		assertEquals(200, response.getStatusCode().value());
		assertEquals(rezervacija.getCena(), response.getBody().getCena());
		assertEquals(rezervacija.getBroj_osoba(), response.getBody().getBroj_osoba());
		assertEquals(rezervacija.isPlaceno(), response.getBody().isPlaceno());
	}

	@Test
	@Order(8)
	void testDeleteRezervacija() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/rezervacija/id/" + largestId, HttpMethod.DELETE, null, String.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted!"));
	}

}