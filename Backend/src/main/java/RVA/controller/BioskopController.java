package RVA.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RVA.models.Bioskop;
import RVA.services.BioskopService;

@CrossOrigin
@RestController
public class BioskopController {

	@Autowired
	private BioskopService service;
	
	@GetMapping("/bioskop")
	//Get maping je skracena verzija request maping anotacije
	//@RequestMapping(method = RequestMethod.GET, path = "/dobavljac")
	public List<Bioskop> getAllBioskops(){
		return service.getAll();
	}
	
	@GetMapping("/bioskop/id/{id}")
	public ResponseEntity<?> getBioskopById(@PathVariable int id){
		Optional<Bioskop> bioskop = service.findById(id);
		if(bioskop.isPresent()) {
			return ResponseEntity.ok(bioskop.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/bioskop/adresa/{adresa}")
	public ResponseEntity<?> getBioskopsByAdresa(@PathVariable String adresa){
		List<Bioskop> bioskop = service.getBioskopsByAdresa(adresa);
		if(bioskop.isEmpty()) {
			return ResponseEntity.status(404).body("Resources with Adresa: " + adresa + " do not exist!");
		}
		return ResponseEntity.ok(bioskop);
	}
	
	@PostMapping("/bioskop")
	public ResponseEntity<?> createBioskop(@RequestBody Bioskop bioskop){
		if(service.existsById(bioskop.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Bioskop savedBioskop = service.create(bioskop);
		URI uri = URI.create("/bioskop/id/" + savedBioskop.getId());
		return ResponseEntity.created(uri).body(savedBioskop);
	}
	
	@PutMapping("/bioskop/id/{id}")
	public ResponseEntity<?> updateBioskop(@RequestBody Bioskop bioskop, @PathVariable int id){
		Optional<Bioskop> updatedBioskop = service.update(bioskop, id);
		if(updatedBioskop.isPresent()) {
			return ResponseEntity.ok(updatedBioskop.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" updated because it does not exist!");
	}
	
	@DeleteMapping("/bioskop/id/{id}")
	public ResponseEntity<?> deleteBioskop(@PathVariable int id ){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" deleted because it does not exist!");
	}
}