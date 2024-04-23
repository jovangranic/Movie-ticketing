package RVA.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Rezervacija implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "REZERVACIJA_SEQ_GENERATOR", sequenceName = "REZERVACIJA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REZERVACIJA_SEQ_GENERATOR")
	private int id;
	private int broj_osoba;
	private double cena;
	private Date datum;
	private boolean placeno;

	@ManyToOne // strani kljuc
	@JoinColumn(name = "film")
	private Film film;

	@ManyToOne // strani kljuc
	@JoinColumn(name = "sala")
	private Sala sala;

	public Rezervacija() {

	}

	public Rezervacija(int id, int broj_osoba, double cena_karte, Date datum, boolean placeno, Film film, Sala sala) {
		this.id = id;
		this.broj_osoba = broj_osoba;
		this.cena = cena;
		this.datum = datum;
		this.placeno = placeno;
		this.film = film;
		this.sala = sala;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBroj_osoba() {
		return broj_osoba;
	}

	public void setBroj_osoba(int broj_osoba) {
		this.broj_osoba = broj_osoba;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public boolean isPlaceno() {
		return placeno;
	}

	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

}