package RVA.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RVA.models.*;
import RVA.services.BioskopService;
import RVA.services.SalaService;

@RestController
public class SalaController {

	@Autowired
	private SalaService service;
	
	@Autowired
	private BioskopService bioskopService;
	
	@GetMapping("/sala")
	//Get maping je skracena verzija request maping anotacije
	//@RequestMapping(method = RequestMethod.GET, path = "/porudzbina")
	public List<Sala> getAllSalas(){
		return service.getAll();
	}
	
	@GetMapping("/sala/id/{id}")
	public ResponseEntity<?> getSalaById(@PathVariable int id){
		Optional<Sala> sala = service.findById(id);
		if(sala.isPresent()) {
			return ResponseEntity.ok(sala.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/sala/kapacitet/{kapacitet}")
	public ResponseEntity<?> getSalasByKapacitet(@PathVariable int kapacitet){
		List<Sala> sala = service.getSalasByKapacitet(kapacitet);
		if(sala.isEmpty()) {
			return ResponseEntity.status(404).body("Resources with kapacitet: " + kapacitet + " do not exist!");
		}
		return ResponseEntity.ok(sala);
	}
	
	@PostMapping("/sala")
	public ResponseEntity<?> createSala(@RequestBody Sala sala){
		if(service.existsById(sala.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Sala savedSala = service.create(sala);
		URI uri = URI.create("/sala/id/" + savedSala.getId());
		return ResponseEntity.created(uri).body(savedSala);
	}
	
	@PutMapping("/sala/id/{id}")
	public ResponseEntity<?> updateSala(@RequestBody Sala sala, @PathVariable int id){
		Optional<Sala> updatedSala = service.update(sala, id);
		if(updatedSala.isPresent()) {
			return ResponseEntity.ok(updatedSala.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" updated because it does not exist!");
	}
	
	@DeleteMapping("/sala/id/{id}")
	public ResponseEntity<?> deleteSala(@PathVariable int id ){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" deleted because it does not exist!");
	}
	
	@GetMapping("/sala/bioskop/{foreignKey}")
	public ResponseEntity<?> getSalasByBioskop(@PathVariable int foreignKey){
		Optional<Bioskop> bioskop = bioskopService.findById(foreignKey);
		if(bioskop.isPresent()) {
			List<Sala> sala = service.getByForeignKey(bioskop.get());
			if(sala.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " + foreignKey
						+ " do not exist!");
			}else {
				return ResponseEntity.ok(sala);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
}