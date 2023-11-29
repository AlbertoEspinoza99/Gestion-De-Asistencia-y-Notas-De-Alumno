package com.colegio.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_aula")
public class Aula {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_aula")
	private Integer codigoAula;
	@Column(name = "piso")
	private int pisoAula;
	@Column(name = "numero_aula")
	private String numeroAula;
	
	@ManyToOne
	@JoinColumn(name = "cod_curso")
	private Curso cursoaulas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedidosaula")
	private List<Pedidos_aula_has_articulos> listaPedidos;
	
	
}
