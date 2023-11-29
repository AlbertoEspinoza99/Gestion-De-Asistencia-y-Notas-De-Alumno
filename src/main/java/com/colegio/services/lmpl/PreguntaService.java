package com.colegio.services.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.PreguntaRepository;
import com.colegio.entity.Preguntas;

@Service
public class PreguntaService extends ICRUDlmpl<Preguntas, Integer>{

	@Autowired
	private PreguntaRepository repo;
	
	@Override
	public JpaRepository<Preguntas, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

	
	public Page<Preguntas> listaDePreguntasPorCodExamen(int cod,Pageable pageable){
		
		return repo.listaDePreguntasPorCodExamen(cod, pageable);
		
	}
	
	public List<Preguntas> listaPreguntaPorExamen(int cod){
		return repo.listaPreguntaPorExamen(cod);
	}
	
}
