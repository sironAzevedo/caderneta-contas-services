package com.caderneta.mapper;

import java.util.Date;

import org.mapstruct.Mapper;

import com.caderneta.model.ContaEntity;
import com.caderneta.model.MesEntity;
import com.caderneta.model.StatusContaEntity;
import com.caderneta.model.TipoContaEntity;
import com.caderneta.model.dto.ContaDTO;
import com.caderneta.model.dto.UserDTO;
import com.caderneta.util.Utils;

@Mapper(componentModel = "spring")
public abstract class ContaMapper {

	public ContaEntity toEntity(ContaDTO dto, UserDTO user) {
		
		return ContaEntity
				.builder()
				.codigo(dto.getCodigo())
				.valorConta(Utils.formatValor(dto.getValorConta()))
				.dataVencimento(dto.getDataVencimento())
				.dataPagamento(dto.getDataPagamento())
				.status((StatusContaEntity) Utils.parseObject(dto.getStatus(), new StatusContaEntity()))
				.qtdParcelas(dto.getQtdParcelas())
				.comentario(dto.getComentario())
				.mes((MesEntity) Utils.parseObject(dto.getMes(), new MesEntity()))
				.tipoConta((TipoContaEntity) Utils.parseObject(dto.getTipoConta(), new TipoContaEntity()))
				.usuario(user.getId())
				.createdAt(new Date())
				.build();
	}
	
	public ContaDTO toDTO(ContaEntity entity) {
		return ContaDTO
				.builder()
				.codigo(entity.getCodigo())
				.valorConta(Utils.formatValor(entity.getValorConta()))
				.dataVencimento(entity.getDataVencimento())
				.dataPagamento(entity.getDataPagamento())
				.status(StatusContaMapper.INSTANCE.toDTO(entity.getStatus()))
				.qtdParcelas(entity.getQtdParcelas())
				.comentario(entity.getComentario())
				.mes(MesMapper.INSTANCE.toDTO(entity.getMes()))
				.tipoConta(TipoContaMapper.INSTANCE.toDTO(entity.getTipoConta()))
				.build();
	}

}
