package com.caderneta.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.caderneta.handler.exception.EmptyResultDataAccessException;
import com.caderneta.mapper.StatusContaMapper;
import com.caderneta.model.dto.StatusContaDTO;
import com.caderneta.repository.IStatusContaRepository;
import com.caderneta.service.IStatusContaService;

@Service
public class StatusContaServiceImpl implements IStatusContaService {

	@Autowired
	private IStatusContaRepository repository;

	@Override
	@Cacheable(cacheNames = StatusContaDTO.CACHE_NAME)
	public List<StatusContaDTO> findAll() {
		return repository.findAll().stream().map(StatusContaMapper.INSTANCE::toDTO).collect(Collectors.toList());
	}

	@Override
	public StatusContaDTO findById(Long status) {
		return repository.findById(status).map(StatusContaMapper.INSTANCE::toDTO)
				.orElseThrow(() -> new EmptyResultDataAccessException("Status não encontrada"));
	}
}
