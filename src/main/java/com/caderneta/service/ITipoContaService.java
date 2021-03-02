package com.caderneta.service;

import java.util.List;

import com.caderneta.model.dto.TipoContaDTO;

public interface ITipoContaService {
	
	List<TipoContaDTO> findAll();

	TipoContaDTO findById(Long codigo);
}
