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

import com.caderneta.handler.StandardError;
import com.caderneta.model.dto.TipoContaDTO;
import com.caderneta.service.ITipoContaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/contas/tipo")
@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class) })
public class TipoContaController {

	@Autowired
	private ITipoContaService service;

	@GetMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Find All Type")
	public List<TipoContaDTO> findAll() {
		return service.findAll();
	}

	@ResponseBody
	@GetMapping(value = "/by-code")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Find By Code")
	public TipoContaDTO findById(@RequestParam(value = "code") Long code) {
		return service.findById(code);
	}
}