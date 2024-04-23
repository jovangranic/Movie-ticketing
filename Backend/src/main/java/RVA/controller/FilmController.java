package RVA.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RVA.models.Film;
import RVA.services.FilmService;

@RestController
public class FilmController {

	@Autowired
	private FilmService service;
	
    /*
     * HTTP GET je jedna od HTTP metoda koja je analogna opciji READ iz CRUD
     * operacija. Anotacija @GetMapping se koristi kako bi se mapirao HTTP GET
     * zahtev. Predstavlja skraćenu verziju metode @RequestMapping(method =
     * RequestMethod.GET) U konkretnom slučaju HTTP GET zahtevi (a to je npr.
     * svako učitavanje stranice u browser-u) upućeni na adresu
     * localhost:8083/artikl biće prosleđeni ovoj metodi.
     *
     * Poziv metode artiklRepository.findAll() će vratiti kolekciju koja sadrži
     * sve artikala koji će potom u browseru biti prikazani u JSON formatu
     */
	
	@GetMapping("/film")
	public List<Film> getAllFilms(){
		return service.getAll();
	}
	
	@GetMapping("/film/id/{id}")
	public ResponseEntity<?> getFilmById(@PathVariable int id){
		Optional<Film> film = service.findById(id);
		if(film.isPresent()) {
			return ResponseEntity.ok(film.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID:" + 
		id + " does not exist.");
	}
	
	@GetMapping("/film/naziv/{naziv}")
	public ResponseEntity<?> getFilmsByNaziv(@PathVariable String naziv){
		List<Film> films = service.getFilmsByNaziv(naziv);
		if(films.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources"
					+ " with Naziv: " + naziv + " could not be found.");
		}
		return ResponseEntity.ok(films);
	}
	
	@PostMapping("/film")
	public ResponseEntity<?> createFilm(@RequestBody Film film){
		if(service.existsById(film.getId())) {
			return ResponseEntity.status(409).body("Resource with" +
		" inserted values already exists.");
		} 
		Film savedArtikl = service.create(film);
		URI uri = URI.create("/film/" + savedArtikl.getId());
		return ResponseEntity.created(uri).body(savedArtikl);
	}
	
	@PutMapping("/film/id/{id}")
	public ResponseEntity<?> updateFilm(@RequestBody Film film, @PathVariable int id){
		Optional<Film> updatedFilm = service.update(film, id);
		if(updatedFilm.isPresent()) {
			return ResponseEntity.ok(updatedFilm);
		} 
		return ResponseEntity.status(404).body("Resource with requested ID: " +
		+ id + " cannont be updated as it doesn't exist.");
	}
	
	@DeleteMapping("/film/id/{id}")
	public ResponseEntity<?> deletedFilm(@PathVariable int id) {
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id +
					"has been deleted.");
		}
		return ResponseEntity.status(404).body("Resourse with requested ID: " +
		" cannont be deleted as it doesn't exist.");
				
	}
	
}