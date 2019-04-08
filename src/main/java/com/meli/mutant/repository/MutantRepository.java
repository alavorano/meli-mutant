package com.meli.mutant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.mutant.entity.DNASequence;

public interface MutantRepository extends JpaRepository<DNASequence, Long> {
	
	Long countByIsMutant(boolean isMutant);

}