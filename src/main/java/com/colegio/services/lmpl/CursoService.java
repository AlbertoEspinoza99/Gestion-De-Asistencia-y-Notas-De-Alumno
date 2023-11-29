package com.colegio.services.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.CursosRepository;
import com.colegio.entity.Curso;

@Service
public class CursoService extends ICRUDlmpl<Curso, Integer>{

	@Autowired
	private CursosRepository repo;
	
	
	@Override
	public JpaRepository<Curso, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	public List<Curso> listaCursoPorNombre(String nombre){
		
	return	repo.listaDeCursoPorNombre(nombre);
	}

}
