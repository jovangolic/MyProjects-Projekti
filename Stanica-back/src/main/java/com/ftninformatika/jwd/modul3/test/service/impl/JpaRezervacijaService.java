package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Linija;
import com.ftninformatika.jwd.modul3.test.model.Rezervacija;
import com.ftninformatika.jwd.modul3.test.repository.LinijaRepository;
import com.ftninformatika.jwd.modul3.test.repository.RezervacijaRepository;
import com.ftninformatika.jwd.modul3.test.service.RezervacijaService;



@Service
public class JpaRezervacijaService implements RezervacijaService {

	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	private LinijaRepository linijaRepository;
	
	@Override
	public Rezervacija findOneById(Long id) {
		return rezervacijaRepository.findOneById(id);
	}

	@Override
	public List<Rezervacija> getAll() {
		return rezervacijaRepository.findAll();
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		if(rezervacija.getLinija().getBrojSlobodnihMesta() == 0) {
			return null;
		} 
		else {
			Linija linija = rezervacija.getLinija();
			if(linija.getBrojSlobodnihMesta() - rezervacija.getBrojMesta() >= 0) {
				linija.setBrojSlobodnihMesta(linija.getBrojSlobodnihMesta()-rezervacija.getBrojMesta());
				rezervacija.setLinija(linija);
				return rezervacijaRepository.save(rezervacija);
			}
			else {
				return null;
			}
		}
		
	}

	@Override
	public void deleteLinija(Long id) {
		linijaRepository.deleteById(id);
	}

}
