package RVA.services;

import java.util.List;

import org.springframework.stereotype.Service;

import RVA.models.*;

@Service
public interface RezervacijaService extends CrudService<Rezervacija>{
	
	List<Rezervacija> getRezervacijasByPlaceno(boolean placeno);
	List<Rezervacija> getByForeignKey(Film film);
	List<Rezervacija> getByForeignKey(Sala sala);
	List<Rezervacija> getRezervacijasByCenaLessThan(double cena);

}