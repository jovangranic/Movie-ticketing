package RVA.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

	/*
	 * Artikl klasa predstavlja jedanu Java Bean klasu, znači da se u klasi nalaze varijable
	 * instanci i get i set metode za te varijable, da implementira Serializable interface i da ima,
	 * u ovom slučaju implicitni, prazan konstruktor. Varijable instance u datoj klasi odgovaraju
	 * kolonama u tabeli u bazi podataka.
	 */
	 
	
	/*
	 * @Entity predstavlja JPA anotaciju. Uloga anotacije je da stavi do znanja da se radi
	 * o entitetu koji ima ID i koji se koristi kako bi se omogućila persistencija podataka.
	 * Klasa as @Entity anotacijom predstavlja klasu koja se mapira u tabelu bazi podataka.
	 */
	
	/*
	* @NamedQuery anotacija (konkretna anotacija je kreirana prilikom nastanka ovih JPA klasa)
	* je takođe JPA anotacija koja omogućava da određenom upitu, koji je pisan u Java Persistency
	* Querry Language, date naziv po kom ga kasnije možete referencirati.
	*/
@Entity
public class Film implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*
	* @Id predstavlja JPA anotaciju i označava varijablu instance koja mapira primarni kljuc
	* u bazi podataka. Ukoliko je klasa anotirana sa @Entity obavezno je da ima varijablu
	* instance koja će biti anotirana sa @Id
	*/
	
	/*
	* @SequenceGenerator predstavlja JPA anotaciju koja se koristi kako bi se naveo naziv
	* sekvence u bazi podataka koja će se koristiti za određivanje naredne vrednosti, naziv
	* sekvence u bazi podataka i vrednost za allocatioSize. Anotacija je kreirana automatski
	* sa kreiranjem klase, izuzetak je parametar allocationSize=1 koji određuje da se vrednosti
	* povećavaju za 1. Bez ovog parametra kreirani ID-evi imale bi negativne vrednosti.
	*/

	/*
	* @GeneratedValue je JPA anotacija i označava da će vrednost biti automatski generisana i
	* navodi neophodne parametre, strategiju i generator koji će se koristiti.
	*/
	
	@Id
	@SequenceGenerator(name = "FILM_SEQ_GENERATOR", sequenceName = "FILM_SEQ",
	allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILM_SEQ_GENERATOR")
	private int id;
	private String naziv;
	private int recenzija;
	private int trajanje;
	private String zanr;
	
	// bidirekciona veza (drugi element stranog kljuca)
	@OneToMany(mappedBy = "film")
	@JsonIgnore
	private List<Rezervacija> filmovi;
	
	public Film() {
		
	}
	
	public Film(int id, String naziv, int recenzija, int trajanje, String zanr) {
		this.id = id;
		this.naziv = naziv;
		this.recenzija = recenzija;
		this.trajanje = trajanje;
		this.zanr = zanr;
	}

	public int getRecenzija() {
		return recenzija;
	}

	public void setRecenzija(int recenzija) {
		this.recenzija = recenzija;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public List<Rezervacija> getFilmovi() {
		return filmovi;
	}

	public void setFilmovi(List<Rezervacija> filmovi) {
		this.filmovi = filmovi;
	}
	
	
	
}