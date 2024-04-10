package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Prevoznik;

public interface PrevoznikService {

	Prevoznik findOneById(Long id);
	List<Prevoznik> getAll();
	Prevoznik save(Prevoznik prevoznik);
	
}
