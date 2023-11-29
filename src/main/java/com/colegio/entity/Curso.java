package com.colegio.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_curso")
	private Integer codigoCurso;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "horario_curso")
	private Date horarioDelCurso;
	
	@JsonIgnore
	@OneToMany(mappedBy = "matriculacurso")
	private List<Matricula_alumno_has_curso> listaMatriculacurso;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cursoasistencia")
	private List<Asistencia> listacursoAsistencia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cursoexamen")
	private List<Examen> listaExamen;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cursoaulas")
	private List<Aula> listaAulas;
	
	public Curso() {
		
	}
	
	public Curso(int cod) {
		codigoCurso=cod;
	}
	
}
