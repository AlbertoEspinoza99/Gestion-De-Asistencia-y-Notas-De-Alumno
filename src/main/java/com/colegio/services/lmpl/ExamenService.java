package com.colegio.services.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.ExamenRepository;
import com.colegio.entity.Examen;

@Service
public class ExamenService extends ICRUDlmpl<Examen, Integer>{

	@Autowired
	private ExamenRepository repo;
	
	@Override
	public JpaRepository<Examen, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

	
	public List<Examen> listaExamenPorCurso(int cod){
		return repo.listaExamenPorCurso(cod);
	}
	
	
	
	
}
