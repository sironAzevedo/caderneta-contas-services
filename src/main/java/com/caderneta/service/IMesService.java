package com.caderneta.service;

import java.util.List;

import com.caderneta.model.dto.MesDTO;

public interface IMesService {
	
	List<MesDTO> findAll();

	MesDTO findById(Long codigo);
}
