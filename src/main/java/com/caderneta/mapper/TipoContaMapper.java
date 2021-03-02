package com.caderneta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.caderneta.model.TipoContaEntity;
import com.caderneta.model.dto.TipoContaDTO;

@Mapper
public interface TipoContaMapper {

	TipoContaMapper INSTANCE = Mappers.getMapper( TipoContaMapper.class );
	
	
	@Mappings({
	      @Mapping(target="codigo", source="dto.codigo"),
	      @Mapping(target="tipo", source="dto.tipo"),
	      @Mapping(target="descricao", source="dto.descricao")
	    })
	TipoContaEntity toEntity(TipoContaDTO dto);
	
	@Mappings({
	      @Mapping(target="codigo", source="entity.codigo"),
	      @Mapping(target="tipo", source="entity.tipo"),
	      @Mapping(target="descricao", source="entity.descricao")
	    })
	TipoContaDTO toDTO(TipoContaEntity entity);
}
