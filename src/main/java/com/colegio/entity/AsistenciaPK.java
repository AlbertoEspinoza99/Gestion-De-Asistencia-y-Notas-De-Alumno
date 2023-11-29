package com.colegio.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AsistenciaPK implements Serializable{

	
	@Column(name = "cod_alumno")
	private int codigoAlumno;
	@Column(name = "cod_curso")
	private int codigoCurso;
	
		
	@Override
	public int hashCode() {
		return Objects.hash(codigoAlumno, codigoCurso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsistenciaPK other = (AsistenciaPK) obj;
		return codigoAlumno == other.codigoAlumno && codigoCurso == other.codigoCurso;
	}
	
	
	
	
	
	
	
	
	
}
