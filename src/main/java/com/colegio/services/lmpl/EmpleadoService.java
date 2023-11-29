package com.colegio.services.lmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.EmpleadoRepository;
import com.colegio.entity.Empleado;

@Service
public class EmpleadoService extends ICRUDlmpl<Empleado, Integer>{

	@Autowired
	private EmpleadoRepository repo;
	
	@Override
	public JpaRepository<Empleado, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

	
	
	
}
