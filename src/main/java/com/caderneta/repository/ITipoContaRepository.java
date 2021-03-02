package com.caderneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caderneta.model.TipoContaEntity;

@Repository
public interface ITipoContaRepository extends JpaRepository<TipoContaEntity, Long> {

}
