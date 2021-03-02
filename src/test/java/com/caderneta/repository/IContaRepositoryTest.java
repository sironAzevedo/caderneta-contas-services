package com.caderneta.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.caderneta.model.ContaEntity;
import com.caderneta.util.AccountCreate;

@DataJpaTest
@DisplayName("Tests for User Repository")
class IContaRepositoryTest {
	
	@Autowired
	private IContaRepository repository;
	private ContaEntity account;
	
	@BeforeEach
	void setup() {
		account = AccountCreate.conta();
		account = this.repository.save(account); 
	}

	@Test
    @DisplayName("Find account by user return sucess")
	void when_findAccount_byUser_return_list() {
		List<ContaEntity> accounts = this.repository.findByUsuario(1L);
		assertNotNull(accounts);
	}
	
	@Test
    @DisplayName("Find account by code and user return sucess")
	void when_findAccount_byCodeAndUser_return_Accout() {		
		Optional<ContaEntity> res = this.repository.findByCodigoAndUsuario(account.getCodigo(), account.getUsuario());
		assertNotNull(res);
	}
	
	@Test
    @DisplayName("Find account by mes and user return sucess")
	void when_findAccount_byMesAndUser_return_Accout() {		
		List<ContaEntity> res = this.repository.findByMesAndUsuario(account.getMes(), account.getUsuario());
		assertNotNull(res);
	}
	
	@Test
    @DisplayName("Find account by status and user return sucess")
	void when_findAccount_byStatusAndUser_return_Accout() {
		PageRequest pageable = PageRequest.of(0, 10);
		Page<ContaEntity> res = this.repository.findByStatusAndUsuario(account.getStatus(), account.getUsuario(), pageable);
		assertNotNull(res.getContent());
	}

}
