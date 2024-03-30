package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import RVA.models.Film;
import RVA.models.Sala;
import RVA.models.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer>{

	List<Rezervacija> findByCenaLessThanOrderByCena(double cena);
	List<Rezervacija> findByFilm(Film film);
	List<Rezervacija> findBySala(Sala sala);
	List<Rezervacija> findByPlacenoEquals (boolean placeno);

}