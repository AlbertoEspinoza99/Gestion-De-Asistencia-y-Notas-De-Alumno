package com.colegio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Asistencia;
@Repository
public interface AsitenciaRepository extends JpaRepository<Asistencia, Integer>{

	@Query(value="select * from tb_asistencia where cod_alumno=?1",nativeQuery = true)
	public List<Asistencia> listaAsistenciaPorCodAlumno(int cod);
	
}
