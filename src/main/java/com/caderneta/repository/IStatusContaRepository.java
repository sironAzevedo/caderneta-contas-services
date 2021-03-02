package com.caderneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caderneta.model.StatusContaEntity;

@Repository
public interface IStatusContaRepository extends JpaRepository<StatusContaEntity, Long> {
	 
}
