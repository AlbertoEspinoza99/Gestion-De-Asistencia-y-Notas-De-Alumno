package com.colegio.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.colegio.entity.Cargo;
import com.colegio.entity.Menu;
import com.colegio.entity.Persona;
import com.colegio.services.lmpl.MenuService;
import com.colegio.services.lmpl.PersonaService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/menu")
@CrossOrigin("*")
public class MenuController {

	
	
	@Autowired
	private MenuService serMenu;
	
	@Autowired
	private PersonaService serPersona;
	
	@Autowired
	private HttpSession session;
	
	
	
	
	@GetMapping("/lista/{cod}")
	public ResponseEntity<List<Menu>> listadeMenu(@PathVariable("cod") Integer cod){
		
			
		System.out.println(cod);

		
		List<Menu> lista=serMenu.listaMenuPORcargo(cod);
	//	System.out.println(lista);
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
	
	
	
	
}
