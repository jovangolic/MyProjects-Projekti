package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Linija;
import com.ftninformatika.jwd.modul3.test.service.LinijaService;
import com.ftninformatika.jwd.modul3.test.support.LinijaDtoToLinija;
import com.ftninformatika.jwd.modul3.test.support.LinijaToLinijaDto;
import com.ftninformatika.jwd.modul3.test.web.dto.LinijaDTO;

@RestController
@RequestMapping(value="/api/linije" , produces = MediaType.APPLICATION_JSON_VALUE)
public class LinijaController {
	
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private LinijaToLinijaDto toLinijaDto;
	@Autowired
	private LinijaDtoToLinija toLinija;
	
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO> create(@Validated @RequestBody LinijaDTO dto){
		
		Linija linija = toLinija.convert(dto);
		if(linija.getPrevoznik() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Linija saved = linijaService.save(linija);
		linijaService.brojSlobodnihMesta(saved, saved.getBrojSlobodnihMesta());
		return new ResponseEntity<>(toLinijaDto.convert(saved) ,HttpStatus.CREATED);
	}
	
	//@PreAuthorize("hasRole('KORISNIK')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO> update (@PathVariable Long id,@Validated @RequestBody LinijaDTO dto){
		if(!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Linija linija = toLinija.convert(dto);
		if(linija.getPrevoznik() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Linija update = linijaService.update(linija);
		linijaService.brojSlobodnihMesta(update, update.getBrojSlobodnihMesta());
		return new ResponseEntity<>(toLinijaDto.convert(update) ,HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Linija deleted = linijaService.delete(id);
		if(deleted != null) {
			linijaService.brojSlobodnihMesta(deleted, deleted.getBrojSlobodnihMesta());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN, KORISNIK')")
	@GetMapping(value="/{id}")
	public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id){
		Linija linija = linijaService.findOneById(id);
		if(linija != null) {
			return new ResponseEntity<>(toLinijaDto.convert(linija) ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//@PreAuthorize("hasAnyRole('*')")
	@GetMapping
	public ResponseEntity<List<LinijaDTO>> getAll(@RequestParam(required = false) String destinacija,
			@RequestParam(required = false) Long prevoznikId, @RequestParam(required = false) Double maxCena,
			@RequestParam(value="pageNo" ,defaultValue = "0") int pageNo){
		
		Page<Linija> page = linijaService.pretraga(destinacija, prevoznikId, maxCena, pageNo);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		return new ResponseEntity<>(toLinijaDto.convert(page.getContent()) ,headers,HttpStatus.OK);
	}
}
