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
import com.caderneta.model.dto.MesDTO;
import com.caderneta.repository.IMesRepository;
import com.caderneta.service.IMesService;
import com.caderneta.util.MesCreate;

@SpringBootTest
class MesServiceImplTest {
	
	@Autowired
	private IMesService service;
	
	@MockBean
	private IMesRepository repository;

	@Test
	public void when_findAllMes_return_sucess() {
		when(repository.findAll()).thenReturn(List.of(MesCreate.mes()));
		List<MesDTO> res = service.findAll();
		assertNotNull(res);
	}
	
	@Test
	public void when_findByCode_return_mes() {
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(MesCreate.mes()));
		MesDTO res = service.findById(1L);
		assertNotNull(res);
	}
	
	@Test
	public void when_findByCode_isNotExist_returnEmptyResultDataAccessException() {
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		Assertions.assertThatThrownBy(() -> {
			service.findById(MesCreate.mes().getCodigo());
		}).isInstanceOf(EmptyResultDataAccessException.class);
	}

}
