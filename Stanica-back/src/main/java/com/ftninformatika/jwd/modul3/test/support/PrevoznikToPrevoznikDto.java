package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Prevoznik;
import com.ftninformatika.jwd.modul3.test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikToPrevoznikDto implements Converter<Prevoznik, PrevoznikDTO> {

	@Override
	public PrevoznikDTO convert(Prevoznik prevoznik) {
		PrevoznikDTO dto = new PrevoznikDTO();
		dto.setId(prevoznik.getId());
		dto.setNaziv(prevoznik.getNaziv());
		dto.setAdresa(prevoznik.getAdresa());
		dto.setPib(prevoznik.getPib());
		return dto;
	}
	
	public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici){
		List<PrevoznikDTO> dto = new ArrayList<>();
		for(Prevoznik p: prevoznici) {
			dto.add(convert(p));
		}
		return dto;
	}

}
