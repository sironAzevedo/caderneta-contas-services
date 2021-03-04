package com.caderneta.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.caderneta.handler.exception.EmptyResultDataAccessException;
import com.caderneta.mapper.TipoContaMapper;
import com.caderneta.model.dto.TipoContaDTO;
import com.caderneta.repository.ITipoContaRepository;
import com.caderneta.service.ITipoContaService;

@Service
public class TipoContaServiceImpl implements ITipoContaService {
	
	@Autowired
	private ITipoContaRepository repository;

	@Override
	@Cacheable(cacheNames = TipoContaDTO.CACHE_NAME)
	public List<TipoContaDTO> findAll() {
		return repository.findAll().stream().map(TipoContaMapper.INSTANCE::toDTO).collect(Collectors.toList());
	}

	@Override
	public TipoContaDTO findById(Long codigo) {
		return repository.findById(codigo).map(TipoContaMapper.INSTANCE::toDTO)
				.orElseThrow(() -> new EmptyResultDataAccessException("Mes não encontrada"));
	}

}
