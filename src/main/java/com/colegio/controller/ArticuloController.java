package com.colegio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Articulos_Aula;
import com.colegio.services.lmpl.ArticuloService;

@RestController
@RequestMapping("/articulo")
public class ArticuloController {

	@Autowired
	private ArticuloService serArti;
	
	
	@PostMapping("/registrar")
	public ResponseEntity<Void> registrarArticulo(@RequestBody Articulos_Aula obj){


		if(obj.getCant()<=0) {
			
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
		}
		else {
			
			Articulos_Aula o=serArti.registra(obj);
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Articulos_Aula>> lista(){
		
		List<Articulos_Aula> lista=serArti.listaTodos();
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
	
	@GetMapping("/porid/{cod}")
	public ResponseEntity<Articulos_Aula> obtenerr(@PathVariable("cod")int codd) throws NotFoundException{
		
		Articulos_Aula obj=serArti.buscarPorId(codd);
		
		if(obj==null) {
			throw new NotFoundException(); 
		}else {
			
			return new ResponseEntity<>(obj,HttpStatus.OK);
		}
		
	}
	
	
	@PutMapping("/actualizar")
	public ResponseEntity<Void> actualizar(@RequestBody Articulos_Aula obj) throws NotFoundException{
		
		
		Articulos_Aula obj1=serArti.buscarPorId(obj.getCodigoArticulos());
		
		if(obj1==null) {
			throw new NotFoundException(); 
		}else {
			
			serArti.actualiza(obj);
			
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		}

	}
	
	

	@DeleteMapping("/eliminar/{cod}")
	public ResponseEntity<Void> eliminar(@PathVariable("cod")int codd) throws NotFoundException{
		
		Articulos_Aula obj=serArti.buscarPorId(codd);
		
		if(obj==null) {
			throw new NotFoundException(); 
		}else {
			
			serArti.eliminar(codd);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	
}
