package com.colegio.services.lmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.AulaRepository;
import com.colegio.entity.Aula;

@Service
public class AulaService extends ICRUDlmpl<Aula, Integer>{

	@Autowired
	private AulaRepository repo;
	
	@Override
	public JpaRepository<Aula, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

}
