package com.caderneta.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.caderneta.handler.exception.EmptyResultDataAccessException;
import com.caderneta.model.dto.TipoContaDTO;
import com.caderneta.repository.ITipoContaRepository;
import com.caderneta.service.ITipoContaService;
import com.caderneta.util.TipoContaCreate;

@SpringBootTest
class TipoContaServiceImplTest {

	@Autowired
	private ITipoContaService service;
	
	@MockBean
	private ITipoContaRepository repository;

	@Test
	void when_findAllMes_return_sucess() {
		when(repository.findAll()).thenReturn(List.of(TipoContaCreate.tipoConta()));
		List<TipoContaDTO> res = service.findAll();
		assertNotNull(res);
	}
	
	@Test
	void when_findByCode_return_mes() {
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(TipoContaCreate.tipoConta()));
		TipoContaDTO res = service.findById(1L);
		assertNotNull(res);
	}
	
	@Test
	void when_findByCode_isNotExist_returnEmptyResultDataAccessException() {
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		Assertions
		.assertThatExceptionOfType(EmptyResultDataAccessException.class)
		.isThrownBy(() -> service.findById(1L));
		
	}

}
