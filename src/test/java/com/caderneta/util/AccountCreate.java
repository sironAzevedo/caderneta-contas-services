package com.caderneta.util;

import java.math.BigDecimal;
import java.util.Date;

import com.caderneta.mapper.MesMapper;
import com.caderneta.mapper.StatusContaMapper;
import com.caderneta.mapper.TipoContaMapper;
import com.caderneta.model.ContaEntity;
import com.caderneta.model.dto.ContaDTO;

public final class AccountCreate {

	private AccountCreate() {
		super();
	}
	
	public static ContaEntity conta() {
		return ContaEntity
				.builder()
				.valorConta(BigDecimal.valueOf(1000L))
				.dataVencimento(new Date())
				.dataPagamento(new Date())
				.qtdParcelas(1)
				.comentario("teste")
				.mes(MesCreate.mes())
				.status(StatusContaCreate.status())
				.tipoConta(TipoContaCreate.tipoConta())
				.usuario(1L)
				.createdAt(new Date())
				.build();
	}
	
	public static ContaDTO contaDTO() {
		return ContaDTO
				.builder()
				.codigo(1L)
				.emailUser("teste.silva@email.com")
				.valorConta("1000")
				.dataVencimento(new Date())
				.dataPagamento(new Date())
				.qtdParcelas(1)
				.comentario("teste")
				.mes(MesMapper.INSTANCE.toDTO(MesCreate.mes()))
				.status(StatusContaMapper.INSTANCE.toDTO(StatusContaCreate.status()))
				.tipoConta(TipoContaMapper.INSTANCE.toDTO(TipoContaCreate.tipoConta()))
				.build();
	}
}
