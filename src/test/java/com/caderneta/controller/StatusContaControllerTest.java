package com.caderneta.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.caderneta.service.IStatusContaService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StatusContaController.class)
class StatusContaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IStatusContaService service;

	@Test
	void whenFindAll_thenReturns200() throws Exception {
		mockMvc.perform(get("/v1/contas/status")
		        .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk());	
	}
	
	@Test
	void whenFindByCode_thenReturns200() throws Exception {
		mockMvc.perform(get("/v1/contas/status/by-code")
		        .contentType(MediaType.APPLICATION_JSON)
		        .param("code", "1"))
		        .andExpect(status().isOk());	
	}

}
