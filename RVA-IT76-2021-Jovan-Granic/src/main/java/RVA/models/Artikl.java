package RVA.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

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
public class Artikl implements Serializable{
	
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
	@SequenceGenerator(name = "ARTIKL_SEQ_GENERATOR", sequenceName = "ARTIKL_SEQ",
	allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTIKL_SEQ_GENERATOR")
	private int id;
	private String naziv;
	private String proizvodjac;
	
	//bidirekciona veza (drugi element odnosno obrnuto od 
	//stranog kljuca, kucano zbog performansi preuzimanja podataka)
	
	@OneToMany (mappedBy = "artikl")
	@JsonIgnore
	private List<StavkaPorudzbine> stavke;
	
	public Artikl() {
		
	}
	
	public Artikl(int id, String naziv, String proizvodjac) {
		this.id = id;
		this.naziv = naziv;
		this.proizvodjac = proizvodjac;
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

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	
	
}
