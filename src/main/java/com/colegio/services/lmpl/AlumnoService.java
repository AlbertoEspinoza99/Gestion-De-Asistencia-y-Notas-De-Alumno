package com.colegio.services.lmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.AlumnoRepository;
import com.colegio.entity.Alumno;

@Service
public class AlumnoService extends ICRUDlmpl<Alumno, Integer>{

	@Autowired
	private AlumnoRepository repo;
	
	@Override
	public JpaRepository<Alumno, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

	
	
	
}
