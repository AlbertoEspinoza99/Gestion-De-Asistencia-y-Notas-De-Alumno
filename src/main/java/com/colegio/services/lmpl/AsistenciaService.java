package com.colegio.services.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.AsitenciaRepository;
import com.colegio.entity.Asistencia;

@Service
public class AsistenciaService extends ICRUDlmpl<Asistencia, Integer>{

	@Autowired
	private AsitenciaRepository repo;
	
	@Override
	public JpaRepository<Asistencia, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	public List<Asistencia> listaAsistenciaPorCodAlumno(int cod){
		return repo.listaAsistenciaPorCodAlumno(cod);
	}

}
