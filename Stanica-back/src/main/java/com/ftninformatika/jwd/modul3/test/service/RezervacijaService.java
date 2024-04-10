package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Rezervacija;

public interface RezervacijaService {

	
	Rezervacija findOneById(Long id);
	List<Rezervacija> getAll();
	Rezervacija save(Rezervacija rezervacija);
	
	void deleteLinija(Long id);
}
