package RVA.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import RVA.models.Film;
import RVA.repository.FilmRepository;
import RVA.services.FilmService;

@Component
public class FilmServiceImpl implements FilmService {
	
	/*
	* Anotacija @Autowired se može primeniti nad varijablama instace, setter metodama i
	* konstruktorima. Označava da je neophodno injektovati zavisni objekat. Prilikom
	* pokretanja aplikacije IoC kontejner prolazi kroz kompletan kod tražeči anotacije
	* koje označavaju da je potrebno kreirati objekte. Upotrebom @Autowired anotacije
	* stavljeno je do znanja da je potrebno kreirati objekta klase koja će implementirati
	* repozitorijum AriklRepository i proslediti klasi ArtiklRestController referencu
	* na taj objekat. 
	*/
	
	@Autowired
	private FilmRepository repo;

	@Override
	public List<Film> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}
	
	public Optional<Film> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public Film create(Film t) {
		return repo.save(t);
	}

	@Override
	public Optional<Film> update(Film t, int id) {
		if(existsById(id))
		{
			t.setId(id);
			return Optional.of(repo.save(t));
		}
		return Optional.empty();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<Film> getFilmsByNaziv(String naziv) {
		return repo.findByNazivContainingIgnoreCase(naziv);
	}

}