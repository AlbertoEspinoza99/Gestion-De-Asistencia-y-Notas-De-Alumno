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
public class Pedidos_aula_has_articulosPK implements Serializable{

	
	@Column(name = "cod_aula")
	private int codigoAula;
	@Column(name = "cod_articulos")
	private int codigoArticulos;
	
	@Override
	public int hashCode() {
		return Objects.hash(codigoArticulos, codigoAula);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedidos_aula_has_articulosPK other = (Pedidos_aula_has_articulosPK) obj;
		return codigoArticulos == other.codigoArticulos && codigoAula == other.codigoAula;
	}
	
	
	
	
	
	
	
	
	
}
