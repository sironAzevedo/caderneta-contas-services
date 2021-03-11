package com.caderneta.mapper;

import com.caderneta.model.DashboardEntity;
import com.caderneta.model.dto.DashboardDTO;
import com.caderneta.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DashboardMapper {

    public DashboardDTO toDTO(DashboardEntity entity) {
        return DashboardDTO
                .builder()
                .codigo(entity.getCodigo())
                .mes(entity.getMes())
                .ano(entity.getAno())
                .qtdConta(entity.getQtdConta())
                .totalGastos(Utils.formatValor(entity.getTotalGastos()))
                .build();
    }
}
