package com.caderneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caderneta.model.MesEntity;

@Repository
public interface IMesRepository extends JpaRepository<MesEntity, Long> {

}
