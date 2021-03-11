package com.caderneta.repository;

import com.caderneta.model.DashboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDashboardRepository extends JpaRepository<DashboardEntity, Long> {

    List<DashboardEntity> findByIdUsuario(Long idUsuario);
}
