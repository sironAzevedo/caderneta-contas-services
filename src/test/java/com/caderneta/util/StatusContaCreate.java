package com.caderneta.util;

import com.caderneta.model.StatusContaEntity;

public final class StatusContaCreate {

	private StatusContaCreate() {
		super();
	}
	
	public static StatusContaEntity status() {
		return StatusContaEntity
				.builder()
				.codigo(2L)
				.descricao("PAGO")
				.build();
	}
}
