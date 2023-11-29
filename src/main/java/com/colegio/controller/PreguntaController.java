package com.colegio.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.colegio.entity.Examen;
import com.colegio.entity.Preguntas;
import com.colegio.services.lmpl.PreguntaService;

@RestController
@RequestMapping("/pregunta")
public class PreguntaController {

	@Autowired
	private PreguntaService serPre;
	
	
	@PostMapping("/registrar")
	public ResponseEntity<Void> registrarPregunta(@RequestBody Preguntas obj){
		
	
		serPre.registra(obj);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	
	
	@GetMapping("/porId/{cod}")
	public ResponseEntity<Preguntas> buscar(@PathVariable("cod")int cod) throws NotFoundException{
		
		 Preguntas obj1=serPre.buscarPorId(cod);
		
		 if(obj1==null) {
			 
			 throw new NotFoundException();
		 }
		 else {
			 
			 return new ResponseEntity<>(obj1,HttpStatus.OK);
		 }
		
	}
	
	@PutMapping("/actualizar")
	 public ResponseEntity<Map<String,String>> actualizar(@RequestBody Preguntas obj) throws NotFoundException{
		 
		 
		 Preguntas obj1=serPre.buscarPorId(obj.getCodigoPregunta());
		 Map<String, String> map=new HashMap<>();
		 
		 if(obj1==null) {
			 
			 throw new NotFoundException();
		 }
		 
		 serPre.actualiza(obj);
		 map.put("respuesta", "Actualizado con Exito");
		 
		 return new ResponseEntity<>(map,HttpStatus.OK);

		 
	 }
	
	
	@GetMapping("/lista/{cod}")
	public ResponseEntity<List<Preguntas>> lista(@PathVariable("cod")int cod) throws NotFoundException{
		
		List<Preguntas> lista=serPre.listaTodos();
		List<Preguntas> nuevo=new ArrayList<>();
		
		if(lista.size()<0) {
			
			 throw new NotFoundException();
			
		}
		else {
			
			
			for(int i=0;i<lista.size();i++)
			{
				
				if(lista.get(i).getPreguntaexamen().getCodigoExamen()==cod) {
					
					nuevo.add(lista.get(i));
					
				}
				
				
			}
			 return new ResponseEntity<>(nuevo,HttpStatus.OK);
			
		}
	}
	
	
	 
    @DeleteMapping("/eliminar/{cod}")
    public ResponseEntity<Void> eliminar(@PathVariable("cod")int cod) throws NotFoundException{
    	
    	 Preguntas obj1=serPre.buscarPorId(cod);
    	
    	
    	 if(obj1==null)
    	 {
    		 
    		 throw new NotFoundException();
    	 }else {
    		 
    		 serPre.eliminar(cod);
    		 
    		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    		
    	 }
    }
	
	
	
	
}
