package com.colegio.entity;

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
@Table(name = "tb_estado")
public class Estado {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_estado")
	private Integer codigoestado;
	@Column(name = "descripcion")
	private String estado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "empleadoestado")
	private List<Empleado> listaEmpleado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "alumnoestado")
	private List<Alumno> listaAlumno;

	public Estado() {
		super();
	}
	
	public Estado(int cod) {
		codigoestado=cod;
	}
	
	
	
}
