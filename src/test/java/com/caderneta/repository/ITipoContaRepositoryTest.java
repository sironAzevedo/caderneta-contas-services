package com.caderneta.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.caderneta.model.TipoContaEntity;

@DataJpaTest
@DisplayName("Tests for Tipo Repository")
class ITipoContaRepositoryTest {

	@Autowired
	private ITipoContaRepository repository;

	@Test
	void when_findAllStatus_return_sucess() {
		List<TipoContaEntity> res = repository.findAll();
		assertNotNull(res); 
	}
	
	@Test
	void when_findStatusByCode_return_sucess() {
		Optional<TipoContaEntity> res = repository.findById(1L);
		assertEquals("ALUGUEL", res.get().getDescricao());
	}

}
