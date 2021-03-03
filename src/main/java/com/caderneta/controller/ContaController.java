package com.caderneta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caderneta.model.dto.ContaDTO;
import com.caderneta.service.IContaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/v1/contas")
public class ContaController {

	@Autowired
	private IContaService service;

	@PostMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	@Operation(summary = "Create Conta")
	public void create(@Valid @RequestBody ContaDTO dto) {
		service.create(dto);
	}

	@PutMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Update Conta")
	public void update(@Valid @RequestBody ContaDTO dto) {
		service.update(dto);
	}

	@ResponseBody
	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@Operation(summary = "Delete Conta")
	public void delete(@RequestParam(value = "email") String user, @RequestParam(value = "conta") Long id) {
		service.delete(user, id);
	}

	@ResponseBody
	@GetMapping("/by-code")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Find by user and code")
	public ContaDTO findById(@RequestParam(value = "email") String user, @RequestParam(value = "conta") Long id) {
		return service.findById(user, id);
	}

	@ResponseBody
	@GetMapping("/by-mes")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Find by Mes")
	public List<ContaDTO> findByMes(@RequestParam(value = "email") String user, @RequestParam(value = "mes") Long mes) {
		return service.findByMes(user, mes);
	}

	@ResponseBody
	@GetMapping("/by-status")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Find by Status")
	public Page<ContaDTO> findByStatus(@RequestParam(value = "email") String user,
			@RequestParam(value = "status") Long status, @ParameterObject Pageable pageable) {
		return service.findByStatus(user, status, pageable);
	}
}
