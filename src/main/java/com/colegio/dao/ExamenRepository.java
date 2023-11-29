package com.colegio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Examen;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Integer>{

	
	
	@Query("select x from Examen x where x.cursoexamen.codigoCurso=?1")
	public List<Examen> listaExamenPorCurso(int cod);
	
	
	
	
	
}
