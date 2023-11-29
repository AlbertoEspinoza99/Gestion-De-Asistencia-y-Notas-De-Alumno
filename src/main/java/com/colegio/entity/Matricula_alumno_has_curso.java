package com.colegio.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_matricula_alumno_has_curso")
public class Matricula_alumno_has_curso {


	@EmbeddedId
	private Matricula_alumno_has_cursoPK id;
	
	@ManyToOne
	@JoinColumn(name = "cod_alumno",insertable = false,updatable = false ,referencedColumnName = "cod_alumno")
	private Alumno matricula;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_curso",insertable = false,updatable = false ,referencedColumnName = "cod_curso")
	private Curso matriculacurso;
	
	
}
