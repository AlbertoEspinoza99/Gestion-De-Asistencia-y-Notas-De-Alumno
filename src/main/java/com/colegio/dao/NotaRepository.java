package com.colegio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.colegio.entity.Nota;

public interface NotaRepository extends JpaRepository<Nota, Integer>{

	
	@Query(value="select * from tb_nota where cod_alumno=?1",nativeQuery=true)
	public List<Nota> listaNotaPorAlumno(int cod);
	
	
}
