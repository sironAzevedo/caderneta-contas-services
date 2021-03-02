package com.caderneta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.caderneta.model.MesEntity;
import com.caderneta.model.dto.MesDTO;

@Mapper
public interface MesMapper {

	MesMapper INSTANCE = Mappers.getMapper( MesMapper.class );
	
	@Mappings({
	      @Mapping(target="codigo", source="dto.codigo"),
	      @Mapping(target="dsMes", source="dto.dsMes")
	    })
	MesEntity toEntity(MesDTO dto);
	
	@Mappings({
	      @Mapping(target="codigo", source="entity.codigo"),
	      @Mapping(target="dsMes", source="entity.dsMes")
	    })
	MesDTO toDTO(MesEntity entity);
}
