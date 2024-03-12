package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import RVA.models.Dobavljac;
import RVA.models.Porudzbina;

public interface PorudzbinaRepository extends 
JpaRepository<Porudzbina, Integer> {

	List<Porudzbina> findByPlacenoEquals (boolean placeno);
	
	//pretraga po stranom kljucu; daje listu porudzbina na osnovu dobavljaca
	List<Porudzbina> findByDobavljac (Dobavljac dobavljac);
	
}
