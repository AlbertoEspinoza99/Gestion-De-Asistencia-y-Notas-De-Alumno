package com.colegio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tb_preguntas")
public class Preguntas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_pregunta")
	private Integer codigoPregunta;
	@Column(name = "contenido")
	private String contenido;
	@Column(name = "imagen")
	private String imagen;
	@Column(name = "pregunta_uno")
	private String preguntaUno;
	@Column(name = "pregunta_dos")
	private String preguntaDos;
	@Column(name = "pregunta_tres")
	private String preguntaTres;
	@Column(name = "pregunta_cuatro")
	private String preguntaCuatro;
	@Column(name = "respuesta")
	private String respuesta;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_examen")
	private Examen preguntaexamen;
	
	
	
}
