package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import RVA.models.Artikl;
import RVA.models.Porudzbina;
import RVA.models.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends 
JpaRepository<StavkaPorudzbine, Integer> {

	List<StavkaPorudzbine> findByCenaGreaterThanOrderByCena (double cena);
	List<StavkaPorudzbine> findByArtikl (Artikl artikl);
	List<StavkaPorudzbine> findByPorudzbina (Porudzbina porudzbina);
	
}
