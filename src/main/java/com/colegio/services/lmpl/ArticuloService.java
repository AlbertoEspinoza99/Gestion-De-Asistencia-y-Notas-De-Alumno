package com.colegio.services.lmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.ArticuloRepository;
import com.colegio.entity.Articulos_Aula;

@Service
public class ArticuloService extends ICRUDlmpl<Articulos_Aula, Integer>{

	@Autowired
	private ArticuloRepository repo;

	@Override
	public JpaRepository<Articulos_Aula, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	
}
