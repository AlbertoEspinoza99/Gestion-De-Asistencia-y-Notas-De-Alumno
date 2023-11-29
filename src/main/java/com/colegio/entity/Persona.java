package com.colegio.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_persona")
	private Integer codPersona;
	@Column(name = "nombre_persona",nullable = false,length = 300)
	private String nombre;
	@Column(name = "apellido_paterno",nullable = false,length = 300)
	private String apellidoPaterno;
	@Column(name = "apellido_materno",nullable = false,length = 300)
	private String apellidoMaterno;
	@Column(name = "username",nullable = false,length = 300)//unique=true
	private String username;
	@Column(name = "dni",nullable = false)
	private int dni;
	@Column(name = "password",nullable = false,length = 300)
	private String password;
	@Column(name = "correo",nullable = false,length = 300)
	private String correo;
	@Column(name = "numero",nullable = false)
	private int numero;
	@Column(name = "fecha_registro")
	private LocalDate fechaRegistro;
	@Column(name = "fecha_nacimineto")
	private Date fechaNacimineto;
	
	 @ManyToMany(fetch=FetchType.EAGER)
	    @JoinTable(
	        name="cargo",
	        joinColumns = {@JoinColumn(name="cod_persona")},
	        inverseJoinColumns = {@JoinColumn(name="cod_cargo")}
	    )
	private Set<Cargo> cargopersona;
	
	
	public Persona() {
		super();
		this.cargopersona=new HashSet<Cargo>();
	}
	
	
	
	
	public Persona(Integer codPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String username,
			int dni, String password, String correo, int numero, LocalDate fechaRegistro, Date fechaNacimineto,Set<Cargo> cargopersona) {
		super();
		this.codPersona = codPersona;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.username = username;
		this.dni = dni;
		this.password = password;
		this.correo = correo;
		this.numero = numero;
		this.fechaRegistro = fechaRegistro;
		this.fechaNacimineto = fechaNacimineto;
		this.cargopersona=cargopersona;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		return this.cargopersona;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	public Persona(int cod) {
		codPersona=cod;
	}






	
	
	
	
	
	
	
	
	
}
