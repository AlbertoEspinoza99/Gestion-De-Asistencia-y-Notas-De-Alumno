package com.colegio.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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
import com.colegio.entity.Aula;
import com.colegio.services.lmpl.AulaService;

@RestController
@RequestMapping("/aula")
public class AulaController {

	@Autowired
	private AulaService serAula;
	
	@PostMapping("/registrar")
	public ResponseEntity<Void> registrarAula(@RequestBody Aula obj){
		
		List<Aula> lista=serAula.listaTodos();
		
		for(int i=0;i<lista.size();i++) {
			
			if(lista.get(i).getCursoaulas().getCodigoCurso()==obj.getCursoaulas().getCodigoCurso()) {
				
				return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			}
			
		}
		
		Aula oo=serAula.registra(obj);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Aula>> listaAula(){
		
		
		List<Aula> lista=serAula.listaTodos();
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/porid/{cod}")
	public ResponseEntity<Aula> obtenerr(@PathVariable("cod")int codd) throws NotFoundException{
		
		Aula obj=serAula.buscarPorId(codd);
		
		if(obj==null) {
			throw new NotFoundException(); 
		}else {
			
			return new ResponseEntity<>(obj,HttpStatus.OK);
		}
		
	}
	
	
	
	
	@PutMapping("/actualizar")
	public ResponseEntity<Void> actualizar(@RequestBody Aula obj) throws NotFoundException{
		
		
		Aula obj1=serAula.buscarPorId(obj.getCodigoAula());
		
		if(obj1==null) {
			throw new NotFoundException(); 
		}else {
			
			serAula.actualiza(obj);
			
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		}

	}
	
	

	@DeleteMapping("/eliminar/{cod}")
	public ResponseEntity<Void> eliminar(@PathVariable("cod")int codd) throws NotFoundException{
		
		Aula obj=serAula.buscarPorId(codd);
		
		if(obj==null) {
			throw new NotFoundException(); 
		}else {
			
			serAula.eliminar(codd);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	
	
	
	
	
}
