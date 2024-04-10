package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Rezervacija;
import com.ftninformatika.jwd.modul3.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDto implements Converter<Rezervacija, RezervacijaDTO> {

	@Override
	public RezervacijaDTO convert(Rezervacija rezervacija) {
		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setId(rezervacija.getId());
		dto.setDatumRezervacije(rezervacija.getDatumRezervacije());
		dto.setDestinacija(rezervacija.getDestinacija());
		dto.setBrojMesta(rezervacija.getBrojMesta());
		dto.setPrevoznikId(rezervacija.getPrevoznik().getId());
		dto.setPrevoznikIme(rezervacija.getPrevoznik().getNaziv());
		dto.setLinijaId(rezervacija.getLinija().getId());
		dto.setLinijaDestinacija(rezervacija.getLinija().getDestinacija());
		return dto;
	}
	
	public List<RezervacijaDTO> convert(List<Rezervacija> rezervacije){
		List<RezervacijaDTO> dto = new ArrayList<>();
		for(Rezervacija r: rezervacije) {
			dto.add(convert(r));
		}
		return dto;
	}

}
