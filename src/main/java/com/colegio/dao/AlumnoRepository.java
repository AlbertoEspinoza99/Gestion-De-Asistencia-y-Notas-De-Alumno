package com.colegio.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Alumno;
import com.colegio.entity.Persona;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{

	Optional<Alumno> findByUsername(String username);
}
