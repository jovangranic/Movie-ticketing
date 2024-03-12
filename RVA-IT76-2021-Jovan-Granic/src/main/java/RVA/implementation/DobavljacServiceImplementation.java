package RVA.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import RVA.models.Dobavljac;
import RVA.repository.DobavljacRepository;
import RVA.services.DobavljacService;

public class DobavljacServiceImplementation implements DobavljacService {

	@Autowired
	private DobavljacRepository repo;
	
	@Override
	public List<Dobavljac> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Dobavljac create(Dobavljac t) {
		return repo.save(t);
	}

	@Override
	public Optional<Dobavljac> update(Dobavljac t, int id) {
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
	public List<Dobavljac> getDobavljacsByAdresa(String adresa) {
		return repo.findByAdresaContainingIgnoreCase(adresa);
	}

}
