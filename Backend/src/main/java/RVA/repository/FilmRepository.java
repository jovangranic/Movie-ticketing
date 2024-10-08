package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import RVA.models.Film;

// proširenje Artikl interfejsa JPARepository interfejsom
public interface FilmRepository extends JpaRepository<Film, Integer>{

	/* JPA omogućava manuelno kreiranje upita, kao što je npr. kreiran upit
	 * sa anotacijom @NamedQuery u klasi Artikal, ali i kreiranje upita na osnovu naziva
	 * metoda. Postoji određen broj rezervisanih reči koje možete koristiti u nazivima 
	 * metode, a koje če omogućiti da se iz samog naziva automatski generiše upit. 
	 * Neke od rezervisanih reči su npr. StartsWith, EndsWith, NotContaining, Containing, 
	 * Contains. And, After, Like, OrderBy itd. U konkretnom slučaju upotrebom rezervisanih 
	 * reči definisana je metoda koja će pronalaziti sve artikle koja u nezivu sadrži prosleđeni 
	 * String ingorišući da li ja napisan malim ili valikim slovima.
	 */
	
	List<Film> findByNazivContainingIgnoreCase(String naziv);
}