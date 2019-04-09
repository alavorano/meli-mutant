package com.meli.mutant.entity;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DNASequenceTest {

    @Test
    public void testInvalidDNA() {
    	List<String[]> invalidDNA = new ArrayList<>();
    	invalidDNA.add(null); //Null DNA
    	invalidDNA.add(new String[] {}); //Empty DNA
    	invalidDNA.add(new String[]{"AAAA", "TTTT"}); //Invalid NxN
    	invalidDNA.add(new String[]{"A", "TT", "AAT", "GGGG"}); //Invalid NxN
    	invalidDNA.add(new String[]{"AAAA", "TT5T", "A3GA", "AGTT"}); //Invalid characters
    	invalidDNA.add(new String[] {""}); //Invalid characters
    	for (String[] sequence : invalidDNA) {
    		DNASequence dna = new DNASequence(sequence);
    		assertFalse(dna.isValid());
		}
    }
    
    @Test
    public void testValidDNA() {
    	DNASequence dna = new DNASequence(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		assertTrue(dna.isValid());
    }
    
    @Test
    public void testMutancy() {
    	DNASequence dna = new DNASequence(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		assertTrue(dna.isMutant());
		dna = new DNASequence(new String[]{"ATGCGA","CAGTGC","TTGTGT","AGAAGG","CCTCTA","TCACTG"});
		assertFalse(dna.isMutant());
    }
}
