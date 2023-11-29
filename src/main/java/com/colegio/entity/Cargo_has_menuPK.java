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
public class Cargo_has_menuPK implements Serializable{

	
	@Column(name = "cod_menu")
	private int codigoMenu;
	
	@Column(name = "cod_cargo")
	private int codigoCargo;

	@Override
	public int hashCode() {
		return Objects.hash(codigoCargo, codigoMenu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cargo_has_menuPK other = (Cargo_has_menuPK) obj;
		return codigoCargo == other.codigoCargo && codigoMenu == other.codigoMenu;
	}

 

	
	
	
	
	
	
}
