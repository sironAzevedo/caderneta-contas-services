package com.caderneta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caderneta.model.dto.MesDTO;
import com.caderneta.service.IMesService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/v1/contas/mes")
public class MesController {

	@Autowired
	private IMesService service;

	@GetMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Find All Type")
	public List<MesDTO> findAll() {
		return service.findAll();
	}

	@ResponseBody
	@GetMapping(value = "/by-code")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Find By Code")
	public MesDTO findById(@RequestParam(value = "code") Long code) {
		return service.findById(code);
	}
}
