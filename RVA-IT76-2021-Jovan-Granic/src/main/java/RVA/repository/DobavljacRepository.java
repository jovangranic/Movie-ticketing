package RVA.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import RVA.models.Dobavljac;

public interface DobavljacRepository extends JpaRepository<Dobavljac, Integer> {
	
	List<Dobavljac> findByAdresaContainingIgnoreCase(String adresa);
	
	
}
