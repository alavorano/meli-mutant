package com.meli.mutant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.mutant.entity.DNASequence;
import com.meli.mutant.repository.MutantRepository;
import com.meli.mutant.service.exception.BadRequestException;

import net.sf.json.JSONObject;

@Service
public class MutantService {
	
	@Autowired
	private MutantRepository mutantRepository;
	
	public boolean isMutant(DNASequence dna) {
		if (!dna.isValid()) {
			throw new BadRequestException("DNA is not valid.");
		}
		boolean isMutant = false;
    	isMutant = dna.isMutant();
    	dna.setIsMutant(isMutant);
    	mutantRepository.save(dna);
        return isMutant;
    }

	public String getStats() {
		JSONObject stats = new JSONObject();
		long mutantCount = mutantRepository.countByIsMutant(true);
		long humanCount = mutantRepository.countByIsMutant(false);
		stats.put("count_mutant_dna", mutantCount);
		stats.put("count_human_dna", humanCount);
		stats.put("ratio", getRatio(mutantCount, humanCount));
		return stats.toString();
	}

	private double getRatio(long mutantCount, long humanCount) {
		double ratio;
		if (humanCount != 0) {
			ratio = (double) mutantCount / (double) humanCount;
        } else {
        	ratio = mutantCount == 0 ? 0.0 : 1.0;
        }
        return ratio;
	}
	
}
