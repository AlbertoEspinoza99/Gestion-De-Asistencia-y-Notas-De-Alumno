package com.colegio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Alumno;
import com.colegio.entity.Empleado;
import com.colegio.entity.LoginResponseDTO;
import com.colegio.entity.Persona;
import com.colegio.entity.RegistrationDTO;
import com.colegio.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AutheticationController {

	@Autowired
	private AuthenticationService serAuth;
	
	@PostMapping("/register")
	public Persona registerUser(@RequestBody RegistrationDTO body) {
		
		return serAuth.registerUser(body.getUsername(), body.getPassword());
	}
	
	
	@PostMapping("/login")
	public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {
		return serAuth.loginUser(body.getUsername(), body.getPassword());
	}
	
	
	@PostMapping("/register/admin")
	public Empleado registerAdmin(@RequestBody Empleado obj) {
		return serAuth.registerAdmin(obj);
	}
	
	@PostMapping("/register/profesor")
	public Empleado registerProfesor(@RequestBody Empleado obj) {
		return serAuth.registerProfesor(obj);
	}
	
	@PostMapping("/register/alumno")
	public Alumno registerAlumno(@RequestBody Alumno obj) {
		return serAuth.registerAlumno(obj);
	}
	
	
	
}
