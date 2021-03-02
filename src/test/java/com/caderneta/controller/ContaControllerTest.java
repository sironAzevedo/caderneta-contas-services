package com.caderneta.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.caderneta.service.IContaService;
import com.caderneta.util.AccountCreate;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ContaController.class)
class ContaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IContaService service;

	@Test
	void whenCreateAccountValid_thenReturns201() throws Exception {
		mockMvc.perform(post("/v1/contas")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(AccountCreate.contaDTO())))
		        .andExpect(status().isCreated());	
	}
	
	@Test
	void whenUpdateAccountValid_thenReturns200() throws Exception {
		mockMvc.perform(put("/v1/contas")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(AccountCreate.contaDTO())))
		        .andExpect(status().isOk());	
	}
	
	@Test
	void whenDeleteAccountValid_thenReturns200() throws Exception {
		mockMvc.perform(delete("/v1/contas")
		        .contentType(MediaType.APPLICATION_JSON)
		        .param("email", "email@test.com")
		        .param("conta", "1"))
		        .andExpect(status().isNoContent());	
	}
	
	@Test
	void whenFindById_thenReturns200() throws Exception {
		mockMvc.perform(get("/v1/contas/by-code")
		        .contentType(MediaType.APPLICATION_JSON)
		        .param("email", "email@test.com")
		        .param("conta", "1"))
		        .andExpect(status().isOk());	
	}
	
	@Test
	void whenFindByMes_thenReturns200() throws Exception {
		mockMvc.perform(get("/v1/contas/by-mes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .param("email", "email@test.com")
		        .param("mes", "1"))
		        .andExpect(status().isOk());	
	}
	
	@Test
	void whenFindByStatus_thenReturns200() throws Exception {
		mockMvc.perform(get("/v1/contas/by-status")
		        .contentType(MediaType.APPLICATION_JSON)
		        .param("email", "email@test.com")
		        .param("status", "1")
		        .param("page", "0")
		        .param("size", "10"))
		        .andExpect(status().isOk());	
	}

}
