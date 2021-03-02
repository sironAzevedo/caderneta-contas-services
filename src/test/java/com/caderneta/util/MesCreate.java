package com.caderneta.util;

import com.caderneta.model.MesEntity;

public final class MesCreate {

	private MesCreate() {
		super();
	}
	
	public static MesEntity mes() {
		return MesEntity
				.builder()
				.codigo(1L)
				.dsMes("JANEIRO")
				.build();
	}
}
