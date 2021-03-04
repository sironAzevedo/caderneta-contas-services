package com.caderneta.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.caderneta.handler.exception.EmptyResultDataAccessException;
import com.caderneta.mapper.MesMapper;
import com.caderneta.model.dto.MesDTO;
import com.caderneta.repository.IMesRepository;
import com.caderneta.service.IMesService;

@Service
public class MesServiceImpl implements IMesService {
	
	@Autowired
	private IMesRepository repository;

	@Override
	@Cacheable(cacheNames = MesDTO.CACHE_NAME)
	public List<MesDTO> findAll() {
		return repository.findAll().stream().map(MesMapper.INSTANCE::toDTO).collect(Collectors.toList());
	}

	@Override
	public MesDTO findById(final Long codigo) {
		return repository.findById(codigo).map(MesMapper.INSTANCE::toDTO)
				.orElseThrow(() -> new EmptyResultDataAccessException("Mes não encontrado"));
	}
}
