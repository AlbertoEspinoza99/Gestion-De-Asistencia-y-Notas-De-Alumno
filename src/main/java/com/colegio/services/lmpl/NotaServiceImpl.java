package com.colegio.services.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.NotaRepository;
import com.colegio.entity.Nota;
@Service
public class NotaServiceImpl extends ICRUDlmpl<Nota, Integer>{

	@Autowired
	private NotaRepository repo;
	
	@Override
	public JpaRepository<Nota, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

	
	public List<Nota> listaNotaPorAlumno(int cod){
		return repo.listaNotaPorAlumno(cod);
	}
	
	
}
