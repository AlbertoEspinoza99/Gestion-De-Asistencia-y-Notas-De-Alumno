package com.colegio.services.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.MatriculaRepository;
import com.colegio.entity.Alumno;
import com.colegio.entity.Curso;
import com.colegio.entity.Matricula_alumno_has_curso;
@Service
public class MatriculaService extends ICRUDlmpl<Matricula_alumno_has_curso, Integer>{

	@Autowired
	private MatriculaRepository repo;
	
	@Override
	public JpaRepository<Matricula_alumno_has_curso, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	
	public List<Alumno> listaAlumnoXcodigoCurso(int cod){
		
	 return	repo.listaDeAlumnoPorCodigoCurso(cod);
	}

	
	public List<Curso> listaDeCursosPorAlumno(String username){
		
		return repo.listaDeCursosPorAlumno(username);
		
	}
	
	
}
