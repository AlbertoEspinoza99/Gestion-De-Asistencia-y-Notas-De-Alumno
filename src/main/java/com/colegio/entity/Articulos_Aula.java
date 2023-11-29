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
@Table(name = "tb_articulos")
public class Articulos_Aula {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_articulos")
	private Integer codigoArticulos;
	@Column(name = "nombre")
	private String nombreArticulos;
	@Column(name = "descripcion")
	private String descripcionArticulo;
	
	//nuevo codigo agregado en nviembre 8
	@Column(name = "cantidad")
	private int cant;
	
	@JsonIgnore
	@OneToMany(mappedBy = "articulosaulass")
	private List<Pedidos_aula_has_articulos> listaArticuloss;
	
}
