package com.ftninformatika.jwd.modul3.test.model;


import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rezervacije")
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="datum_rezervacije" ,nullable = false)
	private String datumRezervacije;
	
	@Column(nullable = false, name="destinacija")
	private String destinacija;
	
	@Column(name="broj_mesta",nullable = false)
	private Integer brojMesta;
	
	@ManyToOne
	@JoinColumn(name="prevoznik_id")
	private Prevoznik prevoznik;
	
	@ManyToOne
	@JoinColumn(name="linija_id")
	private Linija linija;
	
	public Rezervacija() {
		super();
	}

	public Rezervacija(Long id, String datumRezervacije, String destinacija, Integer brojMesta, Prevoznik prevoznik,
			Linija linija) {
		super();
		this.id = id;
		this.datumRezervacije = datumRezervacije;
		this.destinacija = destinacija;
		this.brojMesta = brojMesta;
		this.prevoznik = prevoznik;
		this.linija = linija;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatumRezervacije() {
		return datumRezervacije;
	}

	public void setDatumRezervacije(String datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}


	public Integer getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(Integer brojMesta) {
		this.brojMesta = brojMesta;
	}

	
	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
	}

	public Linija getLinija() {
		return linija;
	}

	public void setLinija(Linija linija) {
		this.linija = linija;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rezervacija other = (Rezervacija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Rezervacija [id=" + id + ", datumRezervacije=" + datumRezervacije + ", destinacija=" + destinacija
				+ ", brojMesta=" + brojMesta + ", prevoznik=" + prevoznik.getNaziv() + ", linija=" + linija.getDestinacija() + "]";
	}

}
