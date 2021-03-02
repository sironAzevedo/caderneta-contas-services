package com.caderneta.service;

import java.util.List;

import com.caderneta.model.dto.StatusContaDTO;

public interface IStatusContaService {

	List<StatusContaDTO> findAll();

	StatusContaDTO findById(Long status);

}
