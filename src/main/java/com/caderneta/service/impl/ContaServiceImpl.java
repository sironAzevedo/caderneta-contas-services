package com.caderneta.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.caderneta.mapper.DashboardMapper;
import com.caderneta.model.DashboardEntity;
import com.caderneta.model.dto.*;
import com.caderneta.repository.IDashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caderneta.clients.UserClient;
import com.caderneta.handler.exception.EmptyResultDataAccessException;
import com.caderneta.handler.exception.QuantidadeParcelasException;
import com.caderneta.handler.exception.UserException;
import com.caderneta.mapper.ContaMapper;
import com.caderneta.mapper.MesMapper;
import com.caderneta.mapper.StatusContaMapper;
import com.caderneta.model.MesEntity;
import com.caderneta.model.enums.StatusConta;
import com.caderneta.repository.IContaRepository;
import com.caderneta.service.IContaService;
import com.caderneta.service.IMesService;
import com.caderneta.service.IStatusContaService;
 
@Service
@Transactional
public class ContaServiceImpl implements IContaService {

	@Autowired
	private IContaRepository repository;

	@Autowired
	private IDashboardRepository dashboardRepository;
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private ContaMapper mapper;

	@Autowired
	private DashboardMapper dashboardMapper;
	
	@Autowired
	private IMesService mesService;

	@Autowired
	private IStatusContaService statusService;

	@Override
	@CacheEvict(cacheNames = ContaDTO.CACHE_NAME, allEntries = true)
	public void create(ContaDTO dto) {
		UserDTO user = this.getUser(dto.getEmailUser());
		isParcelado(dto.getStatus().getDescricao(), dto.getQtdParcelas());
		repository.save(mapper.toEntity(dto, user));
	}

	@Override
	@CacheEvict(cacheNames = ContaDTO.CACHE_NAME, allEntries = true)
	public void update(ContaDTO dto) {
		UserDTO user = this.getUser(dto.getEmailUser());
		findById(user.getEmail(), dto.getCodigo());
		isParcelado(dto.getStatus().getDescricao(), dto.getQtdParcelas());
		repository.save(mapper.toEntity(dto, user));
	}

	@Override
	@CacheEvict(cacheNames = ContaDTO.CACHE_NAME, allEntries = true)
	public void delete(String emailUser, Long id) {
		findById(emailUser, id);
		repository.deleteById(id);
	}

	@Override
	@Cacheable(cacheNames = ContaDTO.CACHE_NAME, key="#emailUser + #id")
	public ContaDTO findById(String emailUser, Long id) {
		UserDTO user = this.getUser(emailUser);
		return repository.findByCodigoAndUsuario(id, user.getId()).map(mapper::toDTO).orElseThrow(() -> new EmptyResultDataAccessException("Conta não encontrada"));
	}

	@Override
	public List<ContaDTO> findByMes(String emailUser, Long mes) {
		UserDTO user = this.getUser(emailUser);
		MesDTO mesDTO = mesService.findById(mes);
		
		MesEntity entity = MesMapper.INSTANCE.toEntity(mesDTO);
		return repository.findByMesAndUsuario(entity, user.getId()).stream().map(mapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Cacheable(cacheNames = ContaDTO.CACHE_NAME, key="#emailUser + #status + #pageable.getPageNumber() + #pageable.getPageSize()")
	public Page<ContaDTO> findByStatus(String emailUser, Long status, Pageable pageable) {
		UserDTO user = this.getUser(emailUser);
		StatusContaDTO statusDTO = statusService.findById(status);
		
		List<ContaDTO> result = repository.findByStatusAndUsuario(StatusContaMapper.INSTANCE.toEntity(statusDTO), user.getId(), pageable).stream().map(mapper::toDTO).collect(Collectors.toList());
		return new PageImpl<>(result, pageable, result.size());
	}

	@Override
	@Cacheable(cacheNames = ContaDTO.CACHE_NAME, key="#email + #pageable.getPageNumber() + #pageable.getPageSize()")
	public Page<DashboardDTO> dashboards(String email, Pageable pageable) {
		UserDTO user = this.getUser(email);
		List<DashboardDTO> result = dashboardRepository.findByIdUsuario(user.getId()).stream().map(dashboardMapper::toDTO).collect(Collectors.toList());
		return new PageImpl<>(result, pageable, result.size());
	}

	private void isParcelado(String status, Integer qtdParcelas) {
		if (StatusConta.PARCELADO.getCodigo().equals(status)) {
			if (qtdParcelas == null) {
				throw new QuantidadeParcelasException("Informe a quantidade de parcelas");
			}
		}
	}
	
	private UserDTO getUser(String user) {
		return Optional.ofNullable(userClient.findByEmail(user)).orElseThrow(() -> new UserException("User not found"));
	}
}
