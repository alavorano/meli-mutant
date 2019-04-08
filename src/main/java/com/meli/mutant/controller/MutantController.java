package com.meli.mutant.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.mutant.dto.DNASequenceDto;
import com.meli.mutant.entity.DNASequence;
import com.meli.mutant.service.MutantService;
import com.meli.mutant.service.exception.BadRequestException;

@RestController
public class MutantController {
	
	@Autowired
	private MutantService mutantService;
	
    @PostMapping("/mutant/")
    public ResponseEntity isMutant(@Valid @RequestBody(required=true) DNASequenceDto dna) {
    	return new ResponseEntity(mutantService.isMutant(getDNAFromDto(dna)) ? HttpStatus.OK : HttpStatus.FORBIDDEN);
    }
    
    @GetMapping("/stats")
	public String getStats() {
		return mutantService.getStats();
	}
    
    private DNASequence getDNAFromDto(DNASequenceDto dto) {
		try {
			return new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(dto),  DNASequence.class);
		} catch (IOException e) {
			throw new BadRequestException("Error while getting dna", e);
		}
	}
    
}