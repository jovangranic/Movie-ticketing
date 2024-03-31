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

import RVA.models.Film;
import RVA.models.Sala;
import RVA.models.Rezervacija;
import RVA.services.FilmService;
import RVA.services.SalaService;
import RVA.services.RezervacijaService;

@RestController
public class RezervacijaController {

	@Autowired
	private RezervacijaService service;
	
	@Autowired 
	private FilmService filmService;
	
	@Autowired
	private SalaService salaService;
	
	
	@GetMapping("/rezervacija")
	//Get maping je skracena verzija request maping anotacije
	//@RequestMapping(method = RequestMethod.GET, path = "/artikl")
	public List<Rezervacija> getAllRezervacijas(){
		return service.getAll();
	}
	
	@GetMapping("/rezervacija/id/{id}")
	public ResponseEntity<?> getRezervacijaById(@PathVariable int id){
		Optional<Rezervacija> rezervacija = service.findById(id);
		if(rezervacija.isPresent()) {
			return ResponseEntity.ok(rezervacija.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/rezervacija/placeno/{placeno}")
	public ResponseEntity<?> getRezervacijasByPlaceno(@PathVariable boolean placeno){
		List<Rezervacija> rezervacija = service.getRezervacijasByPlaceno(placeno);
		if(rezervacija.isEmpty()) {
			return ResponseEntity.status(404).body("Resources with Placeno: " + placeno + " do not exist!");
		}
		return ResponseEntity.ok(rezervacija);
	}
	
	@GetMapping("/rezervacija/cena/{cena}")
	public ResponseEntity<?> getRezervacijasByCena(@PathVariable double cena){
		List<Rezervacija> rezervacija = service.getRezervacijasByCenaLessThan(cena);
		if(rezervacija.isEmpty()) {
			return ResponseEntity.status(404).body("Resources with Cena: " + cena + " do not exist!");
		}
		return ResponseEntity.ok(rezervacija);
	}
	
	@PostMapping("/rezervacija")
	public ResponseEntity<?> createRezervacija(@RequestBody Rezervacija rezervacija){
		if(service.existsById(rezervacija.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Rezervacija savedRezervacija = service.create(rezervacija);
		URI uri = URI.create("/rezervacija/id/" + savedRezervacija.getId());
		return ResponseEntity.created(uri).body(savedRezervacija);
	}
	
	@PutMapping("/rezervacija/id/{id}")
	public ResponseEntity<?> updateRezervacija(@RequestBody Rezervacija rezervacija, @PathVariable int id){
		Optional<Rezervacija> updatedRezervacija = service.update(rezervacija, id);
		if(updatedRezervacija.isPresent()) {
			return ResponseEntity.ok(updatedRezervacija.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" updated because it does not exist!");
	}
	
	@DeleteMapping("/rezervacija/id/{id}")
	public ResponseEntity<?> deleteRezervacija(@PathVariable int id ){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" deleted because it does not exist!");
	}
	
	@GetMapping("/rezervacija/film/{foreignKey}")
	public ResponseEntity<?> getRezervacijaByFilm(@PathVariable int foreignKey){
		Optional<Film> film = filmService.findById(foreignKey);
		if(film.isPresent()) {
			List<Rezervacija> rezervacije = service.getByForeignKey(film.get());
			if(rezervacije.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " + foreignKey
						+ " do not exist!");
			}else {
				return ResponseEntity.ok(rezervacije);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
	
	@GetMapping("/rezervacija/sala/{foreignKey}")
	public ResponseEntity<?> getRezervacijaBySala(@PathVariable int foreignKey){
		Optional<Sala> sala = salaService.findById(foreignKey);
		if(sala.isPresent()) {
			List<Rezervacija> rezervacija = service.getByForeignKey(sala.get());
			if(rezervacija.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " + foreignKey
						+ " do not exist!");
			}else {
				return ResponseEntity.ok(rezervacija);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
}