package RVA.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import RVA.models.Artikl;

public interface ArtiklRepository extends JpaRepository<Artikl, Integer> {
	
	List<Artikl> findByNazivContainingIgnoreCase(String naziv);
	
	
	
}
