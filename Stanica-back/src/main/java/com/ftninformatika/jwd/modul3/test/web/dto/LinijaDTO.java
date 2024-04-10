package com.ftninformatika.jwd.modul3.test.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LinijaDTO {

	
	private Long id;
	
	@Positive(message = "Ne sme biti negativna vrednost")
	private Integer brojMesta;
	
	private Double cenaKarte;
	
	private String vremePolaska;
	
	@NotNull
	@NotEmpty
	private String destinacija;
	
	private Long prevoznikId;
	
	private String prevoznikIme;
	
	private Integer brojSlobodnihMesta;

	public LinijaDTO() {
		super();
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

	public Long getPrevoznikId() {
		return prevoznikId;
	}

	public void setPrevoznikId(Long prevoznikId) {
		this.prevoznikId = prevoznikId;
	}

	public String getPrevoznikIme() {
		return prevoznikIme;
	}

	public void setPrevoznikIme(String prevoznikIme) {
		this.prevoznikIme = prevoznikIme;
	}

	public Integer getBrojSlobodnihMesta() {
		return brojSlobodnihMesta;
	}

	public void setBrojSlobodnihMesta(Integer brojSlobodnihMesta) {
		this.brojSlobodnihMesta = brojSlobodnihMesta;
	}
	
	
}
