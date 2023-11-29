package com.colegio.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tb_empleado")
@PrimaryKeyJoinColumn(name = "cod_empleado")
public class Empleado extends Persona {

	@Column(name = "sueldo")
	private Double sueldo;
	
	
	

	
   @ManyToOne
   @JoinColumn(name = "cod_estado")
   private Estado empleadoestado;

   

		public Empleado() {
			super();
		}


		
		
		public Empleado(Integer codPersona, String nombre, String apellidoPaterno, String apellidoMaterno,
				String username, int dni, String password, String correo, int numero, LocalDate fechaRegistro,
				Date fechaNacimineto,Set<Cargo> cargopersona,Double sueldo,Estado empleadoestado) {
			super(codPersona, nombre, apellidoPaterno, apellidoMaterno, username, dni, password, correo, numero, fechaRegistro,
					fechaNacimineto,cargopersona);
			
			this.sueldo=sueldo;		
			this.empleadoestado=empleadoestado;
			
		}


   


	

	
	
	
	
	
	
	
}
