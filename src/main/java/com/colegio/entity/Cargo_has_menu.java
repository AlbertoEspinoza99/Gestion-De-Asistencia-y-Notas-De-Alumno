package com.colegio.entity;

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
@Table(name = "tb_cargo_has_menu")
public class Cargo_has_menu {

	@EmbeddedId
	private Cargo_has_menuPK id;
	
	@ManyToOne
	@JoinColumn(name = "cod_menu",insertable = false,updatable = false,referencedColumnName = "cod_menu")
	private Menu menucargo;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_cargo",insertable = false,updatable = false,referencedColumnName = "cod_cargo")
	private Cargo cargomenu;
	
	
}
