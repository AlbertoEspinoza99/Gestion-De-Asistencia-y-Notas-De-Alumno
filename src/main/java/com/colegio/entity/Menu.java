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
@Table(name = "tb_menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_menu")
	private Integer codigoMenu;
	@Column(name = "nombre")
	private String nombremenu;
	@Column(name = "url")
	private String url;
	
	@JsonIgnore
	@OneToMany(mappedBy = "menucargo")
	private List<Cargo_has_menu> listaCargoMenu;
	
	
	
}
