package com.colegio.entity;


import java.util.List;
import org.springframework.security.core.GrantedAuthority;

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


@Entity
@Table(name = "tb_cargo")
public class Cargo implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cargo")
	private Integer codigoCargo;
	@Column(name = "nombre_cargo")
	private String authority;
	

	
	@JsonIgnore
	@OneToMany(mappedBy = "cargomenu")
	private List<Cargo_has_menu> listacargoMenu;

	
	
	public Cargo() {
		super();
	}

	public Cargo(String authority) {
		this.authority=authority;
	}
	
	
	public Cargo(Integer codigoCargo, String authority) {
		
		this.codigoCargo = codigoCargo;
		this.authority = authority;
	}
	
	
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}


	public Integer getCodigoCargo() {
		return codigoCargo;
	}


	public void setCodigoCargo(Integer codigoCargo) {
		this.codigoCargo = codigoCargo;
	}


	public String getNombreCargo() {
		return authority;
	}


	public void setNombreCargo(String authority) {
		this.authority = authority;
	}


   public Cargo(int cod) {
	   codigoCargo=cod;
   }
	
	
}
