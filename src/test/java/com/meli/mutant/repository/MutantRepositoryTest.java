package com.meli.mutant.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.meli.mutant.entity.DNASequence;
import com.meli.mutant.service.MutantService;

import net.sf.json.JSONObject;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MutantRepositoryTest {
	
	@Autowired
	private MutantRepository mutantRepository;
	
	@Test
	public void testStats() {
		MutantService mutantService = new MutantService(mutantRepository);
		mutantService.isMutant(new DNASequence(new String[] {"AAAA", "TTTT", "AAAA", "TTTT"}));
		mutantService.isMutant(new DNASequence(new String[] {"AC", "TG"}));
		mutantService.isMutant(new DNASequence(new String[] {"AA", "TA"}));
		JSONObject stats = JSONObject.fromObject(mutantService.getStats());
		Assert.assertEquals(1L,  stats.getLong(MutantService.COUNT_MUTANT_DNA));
		Assert.assertEquals(2L,  stats.getLong(MutantService.COUNT_HUMAN_DNA));
		Assert.assertEquals(0.5, stats.get(MutantService.RATIO));
	}
	
	@Test
	public void testRatio() {
		MutantService mutantService = new MutantService(mutantRepository);
		Assert.assertEquals(0.0, mutantService.getRatio(0, 0), 0);
		Assert.assertEquals(0.0, mutantService.getRatio(0, 3), 0);
		Assert.assertEquals(1.0, mutantService.getRatio(3, 0), 0);
		Assert.assertEquals(0.4, mutantService.getRatio(40, 100), 0);
		Assert.assertEquals(2.5, mutantService.getRatio(100, 40), 0);
	}
}
