package com.caderneta.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.caderneta.clients.UserClient;
import com.caderneta.handler.exception.EmptyResultDataAccessException;
import com.caderneta.handler.exception.QuantidadeParcelasException;
import com.caderneta.handler.exception.UserException;
import com.caderneta.mapper.ContaMapper;
import com.caderneta.mapper.MesMapper;
import com.caderneta.mapper.StatusContaMapper;
import com.caderneta.model.ContaEntity;
import com.caderneta.model.MesEntity;
import com.caderneta.model.StatusContaEntity;
import com.caderneta.model.dto.ContaDTO;
import com.caderneta.model.dto.StatusContaDTO;
import com.caderneta.model.dto.UserDTO;
import com.caderneta.repository.IContaRepository;
import com.caderneta.service.IContaService;
import com.caderneta.service.IMesService;
import com.caderneta.service.IStatusContaService;
import com.caderneta.util.AccountCreate;
import com.caderneta.util.MesCreate;
import com.caderneta.util.StatusContaCreate;

@SpringBootTest
class ContaServiceImplTest {
	
	@Autowired
	private IContaService service;
	
	@Autowired
	private ContaMapper mapper;
	
	@MockBean
	private IContaRepository repository;
	
	@MockBean
	private UserClient userClient;
	
	@MockBean
	private IMesService mesService;
	
	@MockBean
	private IStatusContaService statusService;
	
	private UserDTO user;
	private ContaEntity account;
	
	@BeforeEach
	void setup() {
		user = new UserDTO();
		user.setId(1L);
		user.setName("Test da Silva");
		user.setEmail("test.sivla@email.com");
		
		account = AccountCreate.conta();		
	}

	@Test
	void whenCreateAccountValid_thenSucess() {
		ContaDTO dto = mapper.toDTO(account);
		dto.setEmailUser("test.sivla@email.com");
		when(userClient.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		assertNotNull(dto);
		service.create(dto);		
	}
	
	@Test
	void whenUpdateAccountValid_thenSucess() {
		ContaDTO dto = mapper.toDTO(account);
		dto.setCodigo(1000L);
		dto.setEmailUser("test.sivla@email.com");
		
		when(userClient.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		when(this.repository.findByCodigoAndUsuario(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong())).thenReturn(Optional.of(account));
		
		assertNotNull(dto);
		service.update(dto);		
	}
	
	@Test
	void whenDeleteAccountValid_thenSucess() {
		
		when(userClient.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		when(this.repository.findByCodigoAndUsuario(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong())).thenReturn(Optional.of(account));
		
		service.delete("test.sivla@email.com", 1000L);
		verify(repository, times(1)).deleteById(1000L);
	}
	
	@Test
	void when_findAccount_byCode_returnAccount() {
		
		when(userClient.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		when(this.repository.findByCodigoAndUsuario(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong())).thenReturn(Optional.of(account));
		
		ContaDTO res = service.findById("test.sivla@email.com", 1000L);
		assertNotNull(res);
	}
	
	@Test
	void when_findAccount_byMes_returnAccount() {
		
		when(userClient.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		when(statusService.findById(ArgumentMatchers.anyLong())).thenReturn(StatusContaMapper.INSTANCE.toDTO(StatusContaCreate.status()));
		when(this.repository.findByStatusAndUsuario(ArgumentMatchers.any(StatusContaEntity.class), ArgumentMatchers.anyLong(), ArgumentMatchers.any(Pageable.class)))
		.thenReturn(new PageImpl<>(List.of(account)));
		
		PageRequest pageable = PageRequest.of(0, 10);
		Page<ContaDTO> res = service.findByStatus("test.sivla@email.com", 1L, pageable);
		assertNotNull(res.getContent());
	}
	
	@Test
	void when_findAccount_byStatus_returnAccount() {
		
		when(userClient.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		when(mesService.findById(ArgumentMatchers.anyLong())).thenReturn(MesMapper.INSTANCE.toDTO(MesCreate.mes()));
		when(this.repository.findByMesAndUsuario(ArgumentMatchers.any(MesEntity.class), ArgumentMatchers.anyLong())).thenReturn(List.of(account));
		
		List<ContaDTO> res = service.findByMes("test.sivla@email.com", 1L);
		assertNotNull(res);
	}
	
	@Test
	void when_createAccount_with_statusContaParcelado_and_parcelasNull_returnUserException() {
		ContaDTO dto = mapper.toDTO(account);
		
		dto.setEmailUser("test.sivla@email.com");
		StatusContaDTO status = StatusContaDTO
				.builder()
				.codigo(4L)
				.descricao("PARCELADO")
				.build();
		
		dto.setStatus(status);
		dto.setQtdParcelas(null);
		
		
		when(userClient.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		
		Assertions
		.assertThatExceptionOfType(QuantidadeParcelasException.class)
		.isThrownBy(() -> service.create(dto));		
	}
	
	
	@Test
	void when_findByCode_with_userNotExist_returnUserException() {
		
		when(userClient.findByEmail(ArgumentMatchers.anyString())).thenReturn(null);
		
		Assertions
			.assertThatExceptionOfType(UserException.class)
			.isThrownBy(() -> service.findById("test.silva@email.com", 1000L));
	}
	
	@Test
	void when_findByCode_with_AccountNotExist_returnEmptyResultDataAccessException() {
		
		when(userClient.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		when(this.repository.findByCodigoAndUsuario(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		Assertions
		.assertThatExceptionOfType(EmptyResultDataAccessException.class)
		.isThrownBy(() -> service.findById("test.silva@email.com", 1000L));
	}

}
