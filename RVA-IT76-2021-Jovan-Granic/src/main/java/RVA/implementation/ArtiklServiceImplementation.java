package RVA.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import RVA.models.Artikl;
import RVA.repository.ArtiklRepository;
import RVA.services.ArtiklService;

public class ArtiklServiceImplementation implements ArtiklService {

	@Autowired
	private ArtiklRepository repo;
	
	@Override
	public List<Artikl> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Artikl create(Artikl t) {
		return repo.save(t);
	}

	@Override
	public Optional<Artikl> update(Artikl t, int id) {
		if (existsById(id)) {
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
	public List<Artikl> getArtiklsByNaziv(String naziv) {
		return repo.findByNazivContainingIgnoreCase(naziv);
	}

}
