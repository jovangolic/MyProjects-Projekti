package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Linija;
import com.ftninformatika.jwd.modul3.test.web.dto.LinijaDTO;

@Component
public class LinijaToLinijaDto implements Converter<Linija, LinijaDTO> {

	@Override
	public LinijaDTO convert(Linija linija) {
		LinijaDTO dto = new LinijaDTO();
		dto.setId(linija.getId());
		dto.setBrojMesta(linija.getBrojMesta());
		dto.setCenaKarte(linija.getCenaKarte());
		dto.setVremePolaska(linija.getVremePolaska());
		dto.setDestinacija(linija.getDestinacija());
		dto.setPrevoznikId(linija.getPrevoznik().getId());
		dto.setPrevoznikIme(linija.getPrevoznik().getNaziv());
		dto.setBrojSlobodnihMesta(linija.getBrojSlobodnihMesta());
		return dto;
	}
	
	public List<LinijaDTO> convert(List<Linija> linije){
		List<LinijaDTO> dto = new ArrayList<>();
		for(Linija l: linije) {
			dto.add(convert(l));
		}
		return dto;
	}

}
