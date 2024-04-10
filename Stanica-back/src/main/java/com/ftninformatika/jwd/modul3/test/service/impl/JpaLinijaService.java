package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Linija;
import com.ftninformatika.jwd.modul3.test.model.Rezervacija;
import com.ftninformatika.jwd.modul3.test.repository.LinijaRepository;
import com.ftninformatika.jwd.modul3.test.repository.PrevoznikRepository;
import com.ftninformatika.jwd.modul3.test.repository.RezervacijaRepository;
import com.ftninformatika.jwd.modul3.test.service.LinijaService;
import com.ftninformatika.jwd.modul3.test.service.RezervacijaService;

@Service
public class JpaLinijaService implements LinijaService {
	
	@Autowired
	private LinijaRepository linijaRepository;
	
	@Autowired
	private PrevoznikRepository prevoznikRepository;

	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	@Autowired
	private RezervacijaService rezervacijaService;

	@Override
	public Linija findOneById(Long id) {
		// TODO Auto-generated method stub
		return linijaRepository.findOneById(id);
	}

	@Override
	public Linija save(Linija linija) {
		// TODO Auto-generated method stub
		return linijaRepository.save(linija);
	}

	@Override
	public Linija update(Linija linija) {
		// TODO Auto-generated method stub
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		Optional<Linija> linija = linijaRepository.findById(id);
		if(linija.isPresent()) {
			linijaRepository.deleteById(id);
			return linija.get();
		}
		return null;
	}

	@Override
	public Page<Linija> pretraga(String destinacija, Long prevoznikId,Double maxCena, int pageNo) {
		/*if(!destinacija.equals("") || destinacija == null) {
			return linijaRepository.pretraga4(prevoznikId, maxCena, PageRequest.of(pageNo, 3));
		}
		else if(prevoznikId == null) {
			return linijaRepository.pretraga2(destinacija, maxCena, PageRequest.of(pageNo, 3));
		}
		else if(maxCena == null) {
			return linijaRepository.pretraga3(destinacija, prevoznikId, PageRequest.of(pageNo, 3));
		}
		else {
			findAll(pageNo);
		}*/
		
		return linijaRepository.pretraga(destinacija, prevoznikId, maxCena, PageRequest.of(pageNo, 3));
	}

	@Override
	public Page<Linija> findAll(int pageNo) {
		// TODO Auto-generated method stub
		return linijaRepository.findAll(PageRequest.of(pageNo, 3));
	}


	@Override
	public List<Linija> maksimalnaCena(String destinacija, Double cena) {
		List<Linija> linije = linijaRepository.findAll();
		List<Linija> cene = new ArrayList<>();
		for(Linija l : linije) {
			if(l.getCenaKarte() <= cena && l.getDestinacija().equals(destinacija)) {
				cene.add(l);
			}
		}
		return cene;
	}

	@Override
	public void brojSlobodnihMesta(Linija linija, int brojMesta) {
		int trenutniBrojSlobodnihMesta = linija.getBrojMesta() - brojMesta;
		if(trenutniBrojSlobodnihMesta >= 0) {
			linija.setBrojSlobodnihMesta(trenutniBrojSlobodnihMesta);
			linijaRepository.save(linija);
		}
		else {
			 System.out.println("Nema dovoljno slobodnih mesta za rezervaciju.");
		}
	}

	@Override
	public void deleteLinijaWithReservations(Long linijaId) {
		List<Rezervacija> rezervacije = rezervacijaRepository.findAll();
		for(Rezervacija r : rezervacije) {
			if(r.getId() == linijaId) {
				rezervacijaRepository.delete(r);
			}
		}
	}
	
}
