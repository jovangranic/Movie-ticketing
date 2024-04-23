package RVABackendTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
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

import RVA.models.Bioskop;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BioskopControllerIntegrationTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BioskopControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;

	long largestId;

	public void createHighestId() {
		ResponseEntity<List<Bioskop>> response = template.exchange("/bioskop", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Bioskop>>() {
				});
		ArrayList<Bioskop> list = (ArrayList<Bioskop>) response.getBody();
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
	void testGetAllDobavljacs() {
		ResponseEntity<List<Bioskop>> response = template.exchange("/bioskop", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Bioskop>>() {
				});
		int statusCode = response.getStatusCode().value();
		List<Bioskop> bioskops = response.getBody();

		assertEquals(200, statusCode);
		assertNotNull(bioskops);
	}

	@Test
	@Order(2)
	void testGetBioskopsById() {
		int id = 1;
		ResponseEntity<Bioskop> response = template.getForEntity("/bioskop/id/" + id, Bioskop.class);
		int statusCode = response.getStatusCode().value();
		Bioskop bioskop = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(bioskop);
		assertEquals(id, bioskop.getId());
	}

	@Test
	@Order(3)
	void testGetBioskopsByAdresa() {
		String adresa = "B";
		ResponseEntity<List<Bioskop>> response = template.exchange("/bioskop/adresa/" + adresa, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Bioskop>>(){});
		int statusCode = response.getStatusCode().value();
		List<Bioskop> bioskops =  response.getBody();
		String nazivBioskopa =   bioskops.get(0).getNaziv();
		
		assertEquals(200, statusCode );
		assertNotNull(bioskops.get(0));
		assertTrue(nazivBioskopa.startsWith(adresa));	
	}

	@Test
	@Order(4)
	void testCreateBioskop() {
		Bioskop bioskop = new Bioskop();
		bioskop.setNaziv("JUNIT test naziv");
		bioskop.setAdresa("JUNIT test adresa");
		
		HttpEntity<Bioskop> entity = new HttpEntity<Bioskop>(bioskop);
		createHighestId();
		ResponseEntity<Bioskop> response  = template.postForEntity("/bioskop", entity, Bioskop.class);		
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(201, response.getStatusCode().value());
		assertEquals(bioskop.getNaziv(), response.getBody().getNaziv());
		assertEquals(bioskop.getAdresa(), response.getBody().getAdresa());
	}

	@Test
	@Order(5)
	void testUpdateBioskop() {
		Bioskop bioskop = new Bioskop();
		bioskop.setNaziv("Izmenjeni naziv");
		bioskop.setAdresa("Izmenjena adresa");
		
		HttpEntity<Bioskop> entity = new HttpEntity<Bioskop>(bioskop);
		getHighestId();
		ResponseEntity<Bioskop> response  = template.exchange("/bioskop/id/" + largestId, HttpMethod.PUT, entity, Bioskop.class);
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(200, response.getStatusCode().value());
		assertEquals(bioskop.getNaziv(), response.getBody().getNaziv());
		assertEquals(bioskop.getAdresa(), response.getBody().getAdresa());
	}

	@Test
	@Order(6)
	void testDeleteBioskop() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/bioskop/id/" + largestId, HttpMethod.DELETE, null, String.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted!"));
	}
}