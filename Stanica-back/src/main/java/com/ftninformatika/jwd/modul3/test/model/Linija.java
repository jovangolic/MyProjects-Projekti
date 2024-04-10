package com.ftninformatika.jwd.modul3.test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="linije")
public class Linija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="broj_mesta", nullable = false)
	private Integer brojMesta;
	
	@Column(name="cena_karte")
	private Double cenaKarte;
	
	@Column(name="vreme_polaska")
	private String vremePolaska;
	
	@Column(name = "destinacija",nullable = false)
	private String destinacija;
	
	@ManyToOne
	@JoinColumn(name="prevoznik_id")
	private Prevoznik prevoznik;
	
	@OneToMany(mappedBy = "linija", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rezervacija> rezervacije = new ArrayList<>();;
	
	@Column(name="broj_slobodnih_mesta")
	private Integer brojSlobodnihMesta;

	public Linija() {
		super();
	}

	public Linija(Long id, Integer brojMesta, Double cenaKarte, String vremePolaska, String destinacija,
			Prevoznik prevoznik, Integer brojSlobodnihMesta) {
		super();
		this.id = id;
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
		this.prevoznik = prevoznik;
		this.brojSlobodnihMesta = brojSlobodnihMesta;
	}
	
	public void rezervisiMesto(int brojMestaZaRezervaciju) {
		if(brojMestaZaRezervaciju <= this.brojSlobodnihMesta) {
			this.brojSlobodnihMesta -= brojMestaZaRezervaciju;
		}
		else {
			System.out.println("Nema dovoljno slobodnih mjesta za rezervaciju.");
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(Integer brojMesta) {
		this.brojMesta = brojMesta;
	}

	public Double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(Double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public String getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Integer getBrojSlobodnihMesta() {
		return brojSlobodnihMesta;
	}

	public void setBrojSlobodnihMesta(Integer brojSlobodnihMesta) {
		this.brojSlobodnihMesta = brojSlobodnihMesta;
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
		Linija other = (Linija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Linija [id=" + id + ", brojMesta=" + brojMesta + ", cenaKarte=" + cenaKarte + ", vremePolaska="
				+ vremePolaska + ", destinacija=" + destinacija + ", prevoznik=" + prevoznik.getNaziv() + ", rezervacije="
				+ rezervacije + ", brojSlobodnihMesta=" + brojSlobodnihMesta + "]";
	}

}
