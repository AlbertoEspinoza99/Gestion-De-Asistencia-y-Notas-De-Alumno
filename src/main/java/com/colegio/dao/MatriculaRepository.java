package com.colegio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Alumno;
import com.colegio.entity.Curso;
import com.colegio.entity.Matricula_alumno_has_curso;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula_alumno_has_curso, Integer>{
	
	
	@Query("select x from Matricula_alumno_has_curso a join a.matricula x where a.matriculacurso.codigoCurso=?1")
	public List<Alumno> listaDeAlumnoPorCodigoCurso(int cod);

	
	
	@Query("select x from Matricula_alumno_has_curso a join a.matriculacurso x where a.matricula.username=?1")
	public List<Curso> listaDeCursosPorAlumno(String username);
	
	
	
	
}
