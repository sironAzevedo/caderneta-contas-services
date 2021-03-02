package com.caderneta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caderneta.model.ContaEntity;
import com.caderneta.model.MesEntity;
import com.caderneta.model.StatusContaEntity;

@Repository
public interface IContaRepository extends JpaRepository<ContaEntity, Long> {

	@Query("SELECT c FROM ContaEntity c WHERE c.usuario = (:user)")
	List<ContaEntity> findByUsuario(@Param("user") Long user);
	
	Optional<ContaEntity> findByCodigoAndUsuario(Long code, Long user);

	List<ContaEntity> findByMesAndUsuario(MesEntity mes, Long usuario);

	Page<ContaEntity> findByStatusAndUsuario(StatusContaEntity status, Long user, Pageable pageable);

}
