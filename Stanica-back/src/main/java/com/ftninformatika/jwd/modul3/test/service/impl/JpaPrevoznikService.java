package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Prevoznik;
import com.ftninformatika.jwd.modul3.test.repository.PrevoznikRepository;
import com.ftninformatika.jwd.modul3.test.service.PrevoznikService;

@Service
public class JpaPrevoznikService implements PrevoznikService {
	
	@Autowired
	private PrevoznikRepository prevoznikRepository;

	@Override
	public Prevoznik findOneById(Long id) {
		return prevoznikRepository.findOneById(id);
	}

	@Override
	public List<Prevoznik> getAll() {
		return prevoznikRepository.findAll();
	}

	@Override
	public Prevoznik save(Prevoznik prevoznik) {
		 return prevoznikRepository.save(prevoznik);
	}

	
}
