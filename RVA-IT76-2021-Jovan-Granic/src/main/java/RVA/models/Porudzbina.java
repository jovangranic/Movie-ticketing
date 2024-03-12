package RVA.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Porudzbina implements Serializable{
		private static final long serialVersionUID = 1L;
		
		@Id
		@SequenceGenerator(name = "PORUDZBINA_SEQ_GENERATOR", sequenceName = "PORUDZBINA_SEQ",
		allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PORUDZBINA_SEQ_GENERATOR")
		private int id;
		private Date datum;
		private Date isporuceno;
		private double iznos;
		private boolean placeno;
		
		//strani kljuc Dobavljac dobavljac
		@ManyToOne // anotacija koja oznacava strani kljuc
		@JoinColumn(name = "dobavljac")
		private Dobavljac dobavljac;
		
		@OneToMany(mappedBy = "porudzbina")
		@JsonIgnore
		private List<StavkaPorudzbine> stavke;
		
		public Porudzbina() {
			
		}
		
		public Porudzbina (int id, Date datum, Date isporuceno, double iznos,
				boolean placeno, Dobavljac dobavljac) {
			this.id = id;
			this.datum = datum;
			this.isporuceno = isporuceno;
			this.iznos = iznos;
			this.placeno = placeno;
			this.dobavljac = dobavljac;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Date getDatum() {
			return datum;
		}

		public void setDatum(Date datum) {
			this.datum = datum;
		}

		public Date getIsporuceno() {
			return isporuceno;
		}

		public void setIsporuceno(Date isporuceno) {
			this.isporuceno = isporuceno;
		}

		public double getIznos() {
			return iznos;
		}

		public void setIznos(double iznos) {
			this.iznos = iznos;
		}

		public boolean isPlaceno() {
			return placeno;
		}

		public void setPlaceno(boolean placeno) {
			this.placeno = placeno;
		}

		public Dobavljac getDobavljac() {
			return dobavljac;
		}

		public void setDobavljac(Dobavljac dobavljac) {
			this.dobavljac = dobavljac;
		}
		
		
	
}
