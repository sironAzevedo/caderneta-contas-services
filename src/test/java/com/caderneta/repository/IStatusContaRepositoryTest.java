package com.caderneta.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.caderneta.model.StatusContaEntity;

@DataJpaTest
@DisplayName("Tests for Status Repository")
class IStatusContaRepositoryTest {

	@Autowired
	private IStatusContaRepository repository;

	@Test
	void when_findAllStatus_return_sucess() {
		List<StatusContaEntity> res = repository.findAll();
		assertNotNull(res); 
	}
	
	@Test
	void when_findStatusByCode_return_sucess() {
		Optional<StatusContaEntity> res = repository.findById(2L);
		assertEquals("PAGO", res.get().getDescricao());
	}

}
