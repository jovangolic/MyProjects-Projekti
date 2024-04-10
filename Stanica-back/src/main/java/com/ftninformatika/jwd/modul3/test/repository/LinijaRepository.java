package com.ftninformatika.jwd.modul3.test.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Linija;

@Repository
public interface LinijaRepository extends JpaRepository<Linija, Long> {
	
	Linija findOneById(Long id);
	
	
	@Query("SELECT l FROM Linija l WHERE (:destinacija IS NULL OR l.destinacija LIKE %:destinacija%) " +
		       "AND (:prevoznikId IS NULL OR l.prevoznik.id = :prevoznikId) " +
		       "AND (:maxCena IS NULL OR l.cenaKarte <= :maxCena)")
	Page<Linija> pretraga(@Param("destinacija") String destinacija, 
		                      @Param("prevoznikId") Long prevoznikId,  
		                      @Param("maxCena") Double maxCena, 
		                      Pageable pageable);

	
	/*Page<Linija> pretraga2(String destinacija, Double maxCena, PageRequest of);
	Page<Linija> pretraga3(String destinacija, Long prevoznikId, PageRequest of);
	Page<Linija> pretraga4(Long prevoznikId, Double maxCena, PageRequest of);*/
}
