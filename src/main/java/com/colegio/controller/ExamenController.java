package com.colegio.controller;

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
import com.colegio.services.lmpl.ExamenService;
import com.colegio.services.lmpl.PreguntaService;

@RestController
@RequestMapping("/examen")
public class ExamenController {

	@Autowired
	private ExamenService serExam;
	
	@Autowired
	private PreguntaService serPregun;
	
	
	@PostMapping("/registrar")
	public ResponseEntity<Examen> RegistrarExamen(@RequestBody Examen obj){
		
		Examen data=serExam.registra(obj);
		
		return new ResponseEntity<>(data,HttpStatus.OK);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Examen>> listaExamen(){
		
		List<Examen> lista=serExam.listaTodos();
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
	
	@GetMapping("/cod/{c}")
	public ResponseEntity<Examen> examenPorCodigo(@PathVariable("c")int cod){
		
		Examen obj=serExam.buscarPorId(cod);
		
		return new ResponseEntity<>(obj,HttpStatus.OK);
		
	}
	
	
	

	 @DeleteMapping("/eliminar/{cod}")
	 public ResponseEntity<Map<String,String>> eliminar(@PathVariable("cod")int cod){
		 
		 List<Preguntas> listaP=serPregun.listaTodos();
		 
		 Map<String, String> map=new HashMap<>();
		 
		 for(int i=0;i<listaP.size();i++) {
			 
			 if(listaP.get(i).getPreguntaexamen().getCodigoExamen()==cod) {
				 
				 map.put("respuesta", "No se puede eliminar (eliminar sus preguntas)");
				 
				 return new ResponseEntity<>(map,HttpStatus.OK);
				 
			 }
			 
		 }
		 
		 map.put("respuesta", "Eliminado Con exito");
		 serExam.eliminar(cod);
		 return new ResponseEntity<>(map,HttpStatus.OK);
		 
	 }
	 
	 
	 @PutMapping("/actualizar")
	 public ResponseEntity<Map<String,String>> actualizar(@RequestBody Examen obj) throws NotFoundException{
		 
		 
		 Examen obj1=serExam.buscarPorId(obj.getCodigoExamen());
		 Map<String, String> map=new HashMap<>();
		 
		 if(obj1==null) {
			 
			 throw new NotFoundException();
		 }
		 
		 serExam.actualiza(obj);
		 map.put("respuesta", "Actualizado con Exito");
		 
		 return new ResponseEntity<>(map,HttpStatus.OK);

		 
	 }
	 
	 
	 
	 
	 
	 
	
	
}
