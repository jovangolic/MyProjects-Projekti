package com.ftninformatika.jwd.modul3.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Prevoznik;

@Repository
public interface PrevoznikRepository extends JpaRepository<Prevoznik, Long> {

	
	Prevoznik findOneById(Long id);
}
