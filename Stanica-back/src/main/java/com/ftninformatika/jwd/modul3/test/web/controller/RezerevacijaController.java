package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Rezervacija;
import com.ftninformatika.jwd.modul3.test.service.LinijaService;
import com.ftninformatika.jwd.modul3.test.service.RezervacijaService;
import com.ftninformatika.jwd.modul3.test.support.RezervacijaDtoToRezervacija;
import com.ftninformatika.jwd.modul3.test.support.RezervacijaToRezervacijaDto;
import com.ftninformatika.jwd.modul3.test.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value="/api/rezervacije" , produces = MediaType.APPLICATION_JSON_VALUE)
public class RezerevacijaController {
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private RezervacijaDtoToRezervacija toRezervacija;

	@Autowired
	private RezervacijaToRezervacijaDto toRezervacijaDto;
	
	@Autowired
	private LinijaService linijaService;
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> create(@Validated @RequestBody RezervacijaDTO dto){
		
		Rezervacija rezervacija = toRezervacija.convert(dto);
		if(rezervacija.getPrevoznik() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(rezervacija.getLinija() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Rezervacija saved = rezervacijaService.save(rezervacija);
		return new ResponseEntity<>(toRezervacijaDto.convert(saved) ,HttpStatus.CREATED);
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN, KORISNIK')")
	@GetMapping(value="/{id}")
	public ResponseEntity<RezervacijaDTO> getOne(@PathVariable Long id){
		Rezervacija rezervacija = rezervacijaService.findOneById(id);
		if(rezervacija != null) {
			return new ResponseEntity<>(toRezervacijaDto.convert(rezervacija) ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//@PreAuthorize("hasAnyRole('*')")
	@GetMapping
	public ResponseEntity<List<RezervacijaDTO>> getAll(){
		List<Rezervacija> rezervacije = rezervacijaService.getAll();
		return new ResponseEntity<>(toRezervacijaDto.convert(rezervacije) ,HttpStatus.OK);
	}
}
