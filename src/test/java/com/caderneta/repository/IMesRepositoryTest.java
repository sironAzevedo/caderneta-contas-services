package com.caderneta.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.caderneta.model.MesEntity;

@DataJpaTest
@DisplayName("Tests for Mes Repository")
class IMesRepositoryTest {
	
	@Autowired
	private IMesRepository repository;

	@Test
	void when_findAllMes_return_sucess() {
		List<MesEntity> res = repository.findAll();
		assertNotNull(res); 
	}
	
	@Test
	void when_findMesByCode_return_sucess() {
		Optional<MesEntity> res = repository.findById(1L);
		assertEquals("JANEIRO", res.get().getDsMes());
	}

}
