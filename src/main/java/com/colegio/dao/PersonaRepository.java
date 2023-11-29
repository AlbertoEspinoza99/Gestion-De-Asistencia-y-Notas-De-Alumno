package com.colegio.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{

	Optional<Persona> findByUsername(String username);
	
	
	
	
}
