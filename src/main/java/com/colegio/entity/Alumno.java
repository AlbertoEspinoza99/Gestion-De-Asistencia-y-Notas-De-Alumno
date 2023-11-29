package com.colegio.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_alumno")
@PrimaryKeyJoinColumn(name = "cod_alumno")
public class Alumno extends Persona {

	
	
	@Column(name = "imagen")
	private String imagen;
	
	@Column(name = "comida")
	private String comida;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_estado")
	private Estado  alumnoestado;
	
	

	@JsonIgnore
	@OneToMany(mappedBy = "matricula")
	private List<Matricula_alumno_has_curso> listaMatricula;
	
	@JsonIgnore
	@OneToMany(mappedBy = "alumnoasistencia")
	private List<Asistencia> listaasistencia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "alumnonota")
	private List<Nota> listaAlumnoNota;


	
	public Alumno() {
		super();
	}

	public Alumno(int cod) {
		super(cod);
	}


	@Builder
	public Alumno(Integer codPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String username,
			int dni, String password, String correo, int numero, LocalDate fechaRegistro, Date fechaNacimineto,Set<Cargo> cargopersona,
			String imagen,String comida,Estado  alumnoestado,List<Matricula_alumno_has_curso> listaMatricula,
			List<Asistencia> listaasistencia,List<Nota> listaAlumnoNota) {
		super(codPersona, nombre, apellidoPaterno, apellidoMaterno, username, dni, password, correo, numero, fechaRegistro,
				fechaNacimineto,cargopersona);
		
		
		this.imagen=imagen;
		this.comida=comida;
		this.alumnoestado=alumnoestado;
		this.listaMatricula=listaMatricula;
		this.listaasistencia=listaasistencia;
		this.listaAlumnoNota=listaAlumnoNota;
		
	}



	
	
	
	
	
	
	
	
}
