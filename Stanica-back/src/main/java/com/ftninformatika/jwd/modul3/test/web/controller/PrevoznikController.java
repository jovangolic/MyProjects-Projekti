package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Prevoznik;
import com.ftninformatika.jwd.modul3.test.service.PrevoznikService;
import com.ftninformatika.jwd.modul3.test.support.PrevoznikDtoToPrevoznik;
import com.ftninformatika.jwd.modul3.test.support.PrevoznikToPrevoznikDto;
import com.ftninformatika.jwd.modul3.test.web.dto.PrevoznikDTO;

@RestController
@RequestMapping(value="/api/prevoznici" , produces = MediaType.APPLICATION_JSON_VALUE)
public class PrevoznikController {
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private PrevoznikToPrevoznikDto toPrevoznikDto;
	
	@Autowired
	private PrevoznikDtoToPrevoznik toPrevoznik;
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = "application/json")
	public ResponseEntity<PrevoznikDTO> create(@Validated @RequestBody PrevoznikDTO dto){
		Prevoznik prevoznik = toPrevoznik.convert(dto);
		Prevoznik saved = prevoznikService.save(prevoznik);
		return new ResponseEntity<>(toPrevoznikDto.convert(saved) ,HttpStatus.CREATED);
	}
	
	//@PreAuthorize("hasAnyRole('*')")
	@GetMapping
	public ResponseEntity<List<PrevoznikDTO>> getAll(){
		List<Prevoznik> prevoznici = prevoznikService.getAll();
		return new ResponseEntity<>(toPrevoznikDto.convert(prevoznici) ,HttpStatus.OK);
	}

}
