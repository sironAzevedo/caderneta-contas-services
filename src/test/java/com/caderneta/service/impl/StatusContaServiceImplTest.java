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
import com.caderneta.model.dto.StatusContaDTO;
import com.caderneta.repository.IStatusContaRepository;
import com.caderneta.service.IStatusContaService;
import com.caderneta.util.StatusContaCreate;

@SpringBootTest
class StatusContaServiceImplTest {
	
	@Autowired
	private IStatusContaService service;
	
	@MockBean
	private IStatusContaRepository repository;

	@Test
	void when_findAllMes_return_sucess() {
		when(repository.findAll()).thenReturn(List.of(StatusContaCreate.status()));
		List<StatusContaDTO> res = service.findAll();
		assertNotNull(res);
	}
	
	@Test
	void when_findByCode_return_mes() {
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(StatusContaCreate.status()));
		StatusContaDTO res = service.findById(1L);
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
