package RVA.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import RVA.models.Film;
import RVA.models.Sala;
import RVA.models.Rezervacija;
import RVA.repository.RezervacijaRepository;
import RVA.services.RezervacijaService;

@Component
public class RezervacijaServiceImpl implements RezervacijaService{

	@Autowired
	private RezervacijaRepository repo;
	
	@Override
	public List<Rezervacija> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<Rezervacija> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public Rezervacija create(Rezervacija t) {
		return repo.save(t);
	}

	@Override
	public Optional<Rezervacija> update(Rezervacija t, int id) {
		if(existsById(id)) {
			t.setId(id);
			return Optional.of(repo.save(t));
		}
		return Optional.empty();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	public List<Rezervacija> getRezervacijasByPlaceno(boolean placeno) {
		return repo.findByPlacenoEquals(placeno);
	}
	
	@Override
	public List<Rezervacija> getRezervacijasByCenaLessThan(double cena) {
		return repo.findByCenaLessThanOrderByCena(cena);
	}

	@Override
	public List<Rezervacija> getByForeignKey(Film film) {
		return repo.findByFilm(film);
	}

	@Override
	public List<Rezervacija> getByForeignKey(Sala sala) {
		return repo.findBySala(sala);
	}

	
}