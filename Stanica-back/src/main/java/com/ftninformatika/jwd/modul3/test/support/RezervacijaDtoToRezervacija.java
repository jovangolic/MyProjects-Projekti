package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Rezervacija;
import com.ftninformatika.jwd.modul3.test.service.LinijaService;
import com.ftninformatika.jwd.modul3.test.service.PrevoznikService;
import com.ftninformatika.jwd.modul3.test.service.RezervacijaService;
import com.ftninformatika.jwd.modul3.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija> {

	@Autowired
	private RezervacijaService rezervacijaService;	
	@Autowired
	private PrevoznikService prevoznikService;
	@Autowired
	private LinijaService linijaService;
	
	@Override
	public Rezervacija convert(RezervacijaDTO dto) {
		Rezervacija rezervacija = null;
		if(dto.getId() == null) {
			rezervacija = new Rezervacija();
		}
		else {
			rezervacija = rezervacijaService.findOneById(dto.getId());
		}
		if(rezervacija != null) {
			//rezervacija.setId(dto.getId());
			rezervacija.setDatumRezervacije(dto.getDatumRezervacije());
			rezervacija.setDestinacija(dto.getDestinacija());
			rezervacija.setBrojMesta(dto.getBrojMesta());
			rezervacija.setPrevoznik(prevoznikService.findOneById(dto.getPrevoznikId()));
			rezervacija.setLinija(linijaService.findOneById(dto.getLinijaId()));
		}
		return rezervacija;
	}

}
