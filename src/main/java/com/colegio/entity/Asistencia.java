package com.colegio.entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_asistencia")
public class Asistencia {


	@EmbeddedId
	private AsistenciaPK id;
	
	@ManyToOne
	@JoinColumn(name = "cod_alumno",insertable = false,updatable = false,referencedColumnName ="cod_alumno")
	private Alumno alumnoasistencia;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_curso",insertable = false,updatable = false,referencedColumnName ="cod_curso")
	private Curso cursoasistencia;
	
	
	@Column(name = "estado")
	private int estado;
	
	
	
}
