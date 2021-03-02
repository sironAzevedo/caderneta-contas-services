package com.caderneta.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.caderneta.model.dto.ContaDTO;

public interface IContaService {

	void create(ContaDTO conta);

	void update(ContaDTO conta);

	void delete(String emailUser, Long id);

	ContaDTO findById(String emailUser, Long id);

	List<ContaDTO> findByMes(String emailUser, Long mes);

	Page<ContaDTO> findByStatus(String emailUser, Long status, Pageable pageable);

}
