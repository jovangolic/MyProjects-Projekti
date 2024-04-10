package com.ftninformatika.jwd.modul3.test.web.dto;



public class RezervacijaDTO {

	
	private Long id;
	
	private String datumRezervacije;
	
	private String destinacija;
	
	private Integer brojMesta;
	
	private Long prevoznikId;
	
	private String prevoznikIme;
	
	private Long linijaId;
	
	private String linijaDestinacija;

	public RezervacijaDTO() {
		super();
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

	public Long getLinijaId() {
		return linijaId;
	}

	public void setLinijaId(Long linijaId) {
		this.linijaId = linijaId;
	}

	public String getLinijaDestinacija() {
		return linijaDestinacija;
	}

	public void setLinijaDestinacija(String linijaDestinacija) {
		this.linijaDestinacija = linijaDestinacija;
	}
	
	
}
