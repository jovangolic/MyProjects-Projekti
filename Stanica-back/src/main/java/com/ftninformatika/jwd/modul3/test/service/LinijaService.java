package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftninformatika.jwd.modul3.test.model.Linija;
import com.ftninformatika.jwd.modul3.test.model.Rezervacija;

public interface LinijaService {

	
	Linija findOneById(Long id);
	Linija save(Linija linija);
	Linija update(Linija linija);
	Linija delete(Long id);
	Page<Linija> pretraga(String destinacija, Long prevoznikId, Double maxCena, int pageNo);
	Page<Linija> findAll(int pageNo);
	
	//Page<Linija> maksimalnaCena(String destinacija, Double cena, int pageNo);
	List<Linija> maksimalnaCena(String destinacija, Double cena);
	
	public void brojSlobodnihMesta(Linija linija, int brojMesta);
	
	public void deleteLinijaWithReservations(Long linijaId);
}
