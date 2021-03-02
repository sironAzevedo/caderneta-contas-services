package com.caderneta.util;

import com.caderneta.model.TipoContaEntity;

public final class TipoContaCreate {

	private TipoContaCreate() {
		super();
	}
	
	public static TipoContaEntity tipoConta() {
		return TipoContaEntity
				.builder()
				.codigo(1L)
				.tipo("ALUGUEL")
				.descricao("ALUGUEL")
				.build();
	}
}
