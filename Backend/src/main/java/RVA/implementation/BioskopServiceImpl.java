package RVA.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import RVA.models.Bioskop;
import RVA.repository.BioskopRepository;
import RVA.services.BioskopService;

@Component
public class BioskopServiceImpl implements BioskopService {
	
	@Autowired
	private BioskopRepository repo;

	@Override
	public List<Bioskop> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Bioskop create(Bioskop t) {
		return repo.save(t);
	}

	@Override
	public Optional<Bioskop> update(Bioskop t, int id) {
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

	@Override
	public List<Bioskop> getBioskopsByAdresa(String adresa) {
		return repo.findByAdresaContainingIgnoreCase(adresa);
	}

	@Override
	public Optional<Bioskop> findById(int id) {
		return repo.findById(id);
	}

}