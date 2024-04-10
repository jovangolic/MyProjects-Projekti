package com.ftninformatika.jwd.modul3.test.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Linija;
import com.ftninformatika.jwd.modul3.test.service.LinijaService;
import com.ftninformatika.jwd.modul3.test.service.PrevoznikService;
import com.ftninformatika.jwd.modul3.test.web.dto.LinijaDTO;

@Component
public class LinijaDtoToLinija implements Converter<LinijaDTO, Linija> {
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private PrevoznikService prevoznikService;

	@Override
	public Linija convert(LinijaDTO dto) {
		Linija linija = null;
		if(dto.getId() == null) {
			linija = new Linija();
		}
		else {
			linija = linijaService.findOneById(dto.getId());
		}
		if(linija != null) {
			//linija.setId(dto.getId());
			linija.setBrojMesta(dto.getBrojMesta());
			linija.setCenaKarte(dto.getCenaKarte());
			linija.setVremePolaska(dto.getVremePolaska());
			linija.setDestinacija(dto.getDestinacija());
			linija.setPrevoznik(prevoznikService.findOneById(dto.getPrevoznikId()));
			linija.setBrojSlobodnihMesta(dto.getBrojSlobodnihMesta());
		}
		return linija;
	}
	

}
