package com.caderneta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.caderneta.model.StatusContaEntity;
import com.caderneta.model.dto.StatusContaDTO;

@Mapper
public interface StatusContaMapper {

	StatusContaMapper INSTANCE = Mappers.getMapper( StatusContaMapper.class );
	
	
	@Mappings({
	      @Mapping(target="codigo", source="dto.codigo"),
	      @Mapping(target="descricao", source="dto.descricao")
	    })
	StatusContaEntity toEntity(StatusContaDTO dto);
	
	@Mappings({
	      @Mapping(target="codigo", source="entity.codigo"),
	      @Mapping(target="descricao", source="entity.descricao")
	    })
	StatusContaDTO toDTO(StatusContaEntity entity);
}
