package com.colegio.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Preguntas;

@Repository
public interface PreguntaRepository extends JpaRepository<Preguntas, Integer>{

	
	@Query(value="select * from tb_preguntas where cod_examen=?1",countQuery="select count(*) from tb_preguntas where cod_examen=?1",
			nativeQuery=true)
	public Page<Preguntas> listaDePreguntasPorCodExamen(int cod,Pageable pageable);
	
	
	@Query(value="select * from tb_preguntas where cod_examen=?1",nativeQuery=true)
	public List<Preguntas> listaPreguntaPorExamen(int cod);
	
	
}
