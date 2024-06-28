package RVA.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Sala implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SALA_SEQ_GENERATOR", sequenceName = "SALA_SEQ",
	allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALA_SEQ_GENERATOR")
	private int id;
	private int kapacitet;
	private int broj_redova;
	
	@ManyToOne // bidirekciona veza (prvi element stranog kljuca)
	@JoinColumn(name = "bioskop")
	private Bioskop bioskop;
	
	@OneToMany(mappedBy = "sala") //bidirekciona veza (drugi element stranog kljuca)
	@JsonIgnore
	private List<Rezervacija> rezervacije;
	
	public Sala() {
		
	}
	
	public Sala(int id, int kapacitet, int broj_redova, Bioskop bioskop) {
		this.id = id;
		this.kapacitet = kapacitet;
		this.broj_redova = broj_redova;
		this.bioskop = bioskop;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public int getBroj_redova() {
		return broj_redova;
	}

	public void setBroj_redova(int broj_redova) {
		this.broj_redova = broj_redova;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}	
	
}