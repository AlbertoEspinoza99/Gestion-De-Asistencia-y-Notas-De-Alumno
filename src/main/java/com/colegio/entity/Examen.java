package com.colegio.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_examen")
public class Examen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_examen")
	private Integer codigoExamen;
	@Column(name = "activo")
	private int activar;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "numero_preguntas")
	private int numeroPreguntas;
	@Column(name = "puntos")
	private int puntosExamen;
	@Column(name = "titulo")
	private String titulo;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_curso")
	private Curso cursoexamen;
	
	@JsonIgnore
	@OneToMany(mappedBy = "preguntaexamen")
	private List<Preguntas> listaPreguntas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "examennota")
	private List<Nota> listaExamenNota;
	
	
	
}
