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
import org.springframework.web.bind.annotation.PathVariable;

import RVA.models.Sala;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SalaControllerIntegrationTest {
	
	@Autowired
	TestRestTemplate template;

	long largestId;

	public void createHighestId() {
		ResponseEntity<List<Sala>> response = template.exchange("/sala", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Sala>>() {
				});
		ArrayList<Sala> list = (ArrayList<Sala>) response.getBody();
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
	void testGetAllSalas() {
		ResponseEntity<List<Sala>> response = template.exchange("/sala", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Sala>>() {
				});
		int statusCode = response.getStatusCode().value();
		List<Sala> salas = response.getBody();

		assertEquals(200, statusCode);
		assertNotNull(salas);
	}

	@Test
	@Order(2)
	void testFindSalaById() {
		long id = 1;
		ResponseEntity<Sala> response = template.getForEntity("/sala/id/" + id, Sala.class);
		int statusCode = response.getStatusCode().value();
		Sala sala = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(sala);
		assertEquals(id, sala.getId());
	}

	@Test
	@Order(3)
	void testGetSalasByBioskop() {
		long dobavljacId = 1;
		ResponseEntity<List<Sala>> response = template.exchange("/sala/bioskop/" + dobavljacId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Sala>>(){});
		int statusCode = response.getStatusCode().value();
		List<Sala> salas =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(salas.get(0));
		for(Sala p: salas) {
			assertTrue(p.getBioskop().getId() == 1);
		}
	}

	@Test
	@Order(4)
	void testCreateSala() {
		Sala sala = new Sala();
		Date datum = new Date();
		int kapacitet = 2100;
		sala.setKapacitet(kapacitet);
		int broj_redova = 7;
		sala.setBroj_redova(broj_redova);
		sala.setRezervacije(null);
		sala.setBioskop(null);
		
		HttpEntity<Sala> entity = new HttpEntity<Sala>(sala);
		createHighestId();
		ResponseEntity<Sala> response  = template.postForEntity("/sala", entity, Sala.class);		
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(201, response.getStatusCode().value());
		assertEquals(kapacitet, response.getBody().getKapacitet());
		assertEquals(broj_redova, response.getBody().getBroj_redova());
	}

	@Test
	@Order(5)
	void testUpdateSala() {
		Sala sala = new Sala();
		sala.setKapacitet(1000);
		sala.setBroj_redova(100);
		
		HttpEntity<Sala> entity = new HttpEntity<Sala>(sala);
		getHighestId();
		ResponseEntity<Sala> response  = template.exchange("/sala/id/" + largestId, HttpMethod.PUT, entity, Sala.class);
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(200, response.getStatusCode().value());
		assertEquals(1000, response.getBody().getKapacitet());
		assertEquals(100, response.getBody().getBroj_redova());
	}

	@Test
	@Order(6)
	void testDeleteSala() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/sala/id/" + largestId, HttpMethod.DELETE, null, String.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted!"));
	}
	
	@Test
	@Order(7)
	void testGetSalasByKapacitet() {
		double kapacitet = 400;
		ResponseEntity<List<Sala>> response = template.exchange("/sala/kapacitet/" + kapacitet, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Sala>>(){});
		int statusCode = response.getStatusCode().value();
		List<Sala> salas =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(salas.get(0));
		for(Sala s: salas) {
			assertTrue(s.getKapacitet() < kapacitet);
		}
	}
	
}