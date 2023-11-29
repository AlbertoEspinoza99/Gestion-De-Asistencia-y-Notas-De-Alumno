package com.colegio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.colegio.entity.Curso;

public interface CursosRepository extends JpaRepository<Curso, Integer>{

	
	@Query("select x from Curso x where x.nombre=?1")
	public List<Curso> listaDeCursoPorNombre(String nombre);
	
	
}
