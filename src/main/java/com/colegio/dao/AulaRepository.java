package com.colegio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Aula;
@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer>{

}
