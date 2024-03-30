package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import RVA.models.Bioskop;
import RVA.models.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer>{

	List<Sala> findSalasByKapacitet(int kapacitet);
	List<Sala> findByBioskop (Bioskop bioskop);
}