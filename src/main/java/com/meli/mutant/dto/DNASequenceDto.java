package com.meli.mutant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DNASequenceDto {

    private String[] dna;
    
    public DNASequenceDto() {
	}
    
    public void setDna(String[] dna) {
    	this.dna = dna;
    }
    
    public String[] getDna() {
    	return this.dna;
    }
    
}
