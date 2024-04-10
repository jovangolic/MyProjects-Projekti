package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Prevoznik;
import com.ftninformatika.jwd.modul3.test.service.PrevoznikService;
import com.ftninformatika.jwd.modul3.test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDtoToPrevoznik implements Converter<PrevoznikDTO, Prevoznik> {
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Override
	public Prevoznik convert(PrevoznikDTO dto) {
		Prevoznik prevoznik = null;
		if(dto.getId() == null) {
			prevoznik = new Prevoznik();
		}
		else {
			prevoznik = prevoznikService.findOneById(dto.getId());
		}
		if(prevoznik != null) {
			//prevoznik.setId(dto.getId());
			prevoznik.setNaziv(dto.getNaziv());
			prevoznik.setAdresa(dto.getAdresa());
			prevoznik.setPib(dto.getPib());
		}
		return prevoznik;
	}
	

}
