package com.meli.mutant.entity;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang.BooleanUtils;

@Entity
public class DNASequence {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String[] dna;
	@Column(columnDefinition = "boolean default false")
	private Boolean isMutant = false;
	@Transient
	private AtomicInteger mutantSequenceCount = new AtomicInteger(0);
	private static final int TIMES_REPEATED = 4;
	private static final int TOTAL_SEQUENCE_ALLOWED = 2;
    
    public void setId(Integer id) {
    	this.id = id;
    }
    
    public Integer getId() {
    	return this.id;
    }
    
    public void setDna(String[] dna) {
    	this.dna = dna;
    }
    
    public String[] getDna() {
    	return this.dna;
    }
    
    public void setIsMutant(boolean isMutant) {
    	this.isMutant = isMutant;
    }
    
    public boolean getisMutant() {
    	return this.isMutant;
    }
    
    public boolean isMutant() {
		mutantSequenceCount.set(0);
		int dnaLength = getDna().length;
		char[][] matrix = new char[dnaLength][dnaLength];
		fillMatrix(matrix, getDna());
		for (int row = 0; row < dnaLength && mutantSequenceCount.get() < TOTAL_SEQUENCE_ALLOWED; row++) {
			for (int column = 0; column < dnaLength && mutantSequenceCount.get() < TOTAL_SEQUENCE_ALLOWED; column++) {
				checkMutancyFrom(matrix, row, column);
			}
		}
		boolean mutant = mutantSequenceCount.get() >= TOTAL_SEQUENCE_ALLOWED;
		this.setIsMutant(mutant);
		return mutant;
	}
	
    public boolean isValid() {
		return getDna() != null && getDna().length >= TIMES_REPEATED && Arrays.stream(getDna()).allMatch(sequence ->
			sequence.length() == getDna().length && IntStream.range(0, sequence.length()).allMatch(i -> sequence.matches(String.format("[ATCG]{%d}", getDna().length)))
		);
	}

	private void fillMatrix(char[][] matrix, String[] dna) {
		IntStream.range(0, dna.length).forEach(row -> matrix[row] = dna[row].toCharArray());
	}

	private void checkMutancyFrom(char[][] matrix, int row, int column) {
		int n = matrix[0].length;
		boolean checkRight = n - column >= TIMES_REPEATED;
		boolean checkDown = n - row >= TIMES_REPEATED;
		boolean checkLeft = column >= TIMES_REPEATED - 1;
		if (checkRight) {
			addMatch(checkHorizontalMatch(matrix, row, column));
		}
		if (checkDown) {
			addMatch(checkVerticalMatch(matrix, row, column));
			if (checkRight) {
				addMatch(checkDiagonalRightMatch(matrix, row, column));
			}
			if (checkLeft) {
				addMatch(checkDiagonalLeftMatch(matrix, row, column));
			}
		}
	}

	private int addMatch(boolean match) {
		return mutantSequenceCount.addAndGet(BooleanUtils.toInteger(match));
	}

	private boolean checkHorizontalMatch(char[][] matrix, int row, int column) {
		return IntStream.range(1, TIMES_REPEATED)
						.allMatch(i -> matrix[row][column] == matrix[row][column+i]);
	}
	
	private boolean checkVerticalMatch(char[][] matrix, int row, int column) {
		return IntStream.range(1, TIMES_REPEATED)
						.allMatch(j -> matrix[row][column] == matrix[row + j][column]);
	}
	
	private boolean checkDiagonalRightMatch(char[][] matrix, int row, int column) {
		return IntStream.range(1, TIMES_REPEATED)
						.allMatch(j -> matrix[row][column] == matrix[row + j][column + j]);
	}
	
	private boolean checkDiagonalLeftMatch(char[][] matrix, int row, int column) {
		return IntStream.range(1, TIMES_REPEATED)
						.allMatch(j -> matrix[row][column] == matrix[row + j][column - j]);
	}

}
