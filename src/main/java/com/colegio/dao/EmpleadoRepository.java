package com.colegio.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Cargo;
import com.colegio.entity.Empleado;
import com.colegio.entity.Persona;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

	Optional<Empleado> findByUsername(String username);
	
	@Query("SELECT p FROM Persona p WHERE :nombreCargo MEMBER OF p.cargopersona")
	List<Persona> findPersonasByCargo(@Param("nombreCargo") Cargo nombreCargo);
	
	
}
