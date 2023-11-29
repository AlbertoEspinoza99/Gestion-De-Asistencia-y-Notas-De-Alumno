package com.colegio.services.lmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.PersonaRepository;
import com.colegio.entity.Persona;

@Service
public class PersonaService extends ICRUDlmpl<Persona, Integer>{

	@Autowired
	private PersonaRepository repo;
	
	@Override
	public JpaRepository<Persona, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

	
	public Persona personaporUsurname(String user) {
	return	repo.findByUsername(user).orElseThrow();
	}
	
}
