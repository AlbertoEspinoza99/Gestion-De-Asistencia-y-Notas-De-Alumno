package com.colegio.entity;

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
@Table(name = "tb_pedidos_aula_has_articulos")
public class Pedidos_aula_has_articulos {

	@EmbeddedId
	private Pedidos_aula_has_articulosPK id;
	
	@ManyToOne
	@JoinColumn(name = "cod_aula",insertable = false,updatable = false,referencedColumnName = "cod_aula")
	private Aula pedidosaula;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_articulos",insertable = false,updatable = false,referencedColumnName = "cod_articulos")
	private Articulos_Aula articulosaulass;
	
	@Column(name = "cantidad")
	private int cantidadarticulos;
	
	
}
