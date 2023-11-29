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
@Table(name = "tb_nota")
public class Nota {

	@EmbeddedId
	private NotaPK id;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_alumno",insertable = false,updatable = false,referencedColumnName = "cod_alumno")
	private Alumno alumnonota;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_examen",insertable = false,updatable = false,referencedColumnName = "cod_examen")
	private Examen examennota;
	
	
	@Column(name = "nota")
	private Double notaAlumno;
	
	
}
