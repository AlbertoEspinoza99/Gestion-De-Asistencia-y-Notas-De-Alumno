package com.colegio.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Curso;
import com.colegio.services.lmpl.CursoService;


@RequestMapping("/curso/lista")
@RestController
public class CursoRepository {

	@Autowired
	private CursoService serCurso;
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<List<Curso>> litaCurso(){
		
		List<Curso> lista=serCurso.listaTodos();
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/registrar")
	public ResponseEntity<Map<String,String>> registrarCurso(@RequestBody Curso obj){
		System.out.println("--xx-->"+obj.getHorarioDelCurso());
		Map<String, String> map=new HashMap<>();
		List<Curso> lista=serCurso.listaTodos();
		boolean nombre = false;
		boolean fecha = false;
		
		for(int i=0;i<lista.size();i++) {
			
			Date dd=obj.getHorarioDelCurso();
			
			System.out.println("---->"+dd);
			Date horarioDelCurso = lista.get(i).getHorarioDelCurso();
			 nombre=lista.get(i).getNombre().equals(obj.getNombre());
			 if (horarioDelCurso != null) {
			        fecha=horarioDelCurso.equals(obj.getHorarioDelCurso());
			    } else {
			    	map.put("respuesta", "Hubo un error en el sistema Reinicie la sesion");
					return new ResponseEntity<>(map,HttpStatus.OK);	
			    }
		}
		
		if(nombre==true && fecha==true) {
			
			map.put("respuesta", "No puede registrar el curso con la misma fecha");
			return new ResponseEntity<>(map,HttpStatus.OK);	
		}
		
		serCurso.registra(obj);
		map.put("respuesta", "exito");
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/noRepite")
	public ResponseEntity<List<Curso>> listaNombreDeCursos(){
		
		
		List<Curso> listaPrincipal=serCurso.listaTodos();
		
		Map<String, Curso> cursosUnicos= new LinkedHashMap<>();
	
		for(Curso curso:listaPrincipal) {
			
			if(!cursosUnicos.containsKey(curso.getNombre())) {
				
				cursosUnicos.put(curso.getNombre(), curso);
				
			}
		}
		
		List<Curso> listaFinal=new ArrayList<>(cursosUnicos.values());
		
		
		return new ResponseEntity<>(listaFinal,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/porNombre/{nom}")
	public ResponseEntity<List<Curso>> listaPorNombreDeCurso(@PathVariable("nom")String nom){
		
		 List<Curso> lista=serCurso.listaCursoPorNombre(nom);
		
		 return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping("/eliminar/{cod}")
	public ResponseEntity<Void> eliminar(@PathVariable("cod")int cod) throws NotFoundException{
		
		Curso obj=serCurso.buscarPorId(cod);
		
		if(obj==null) {
			
			throw new NotFoundException();
		}else {
			
			
			
			serCurso.eliminar(cod);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/porid/{cod}")
	public  ResponseEntity<Curso> listaPorCodigo(@PathVariable("cod")int cod) throws NotFoundException{
		
		Curso obj=serCurso.buscarPorId(cod);
		
		if (obj==null) {
			throw new NotFoundException();
		}else {
			
			obj=serCurso.buscarPorId(cod);
			
		}
		return new ResponseEntity<>(obj,HttpStatus.OK);
	}
	
	
	
	
	
	@PutMapping("/actualizar")
	public ResponseEntity<Curso> actualizar(@RequestBody Curso obj) throws NotFoundException{
		
		Curso obj2=serCurso.buscarPorId(obj.getCodigoCurso());
		
		
		if (obj2==null) {
			
			throw new NotFoundException();
		}else {
			
		 obj2=serCurso.actualiza(obj);
		}
		
		return new ResponseEntity<>(obj2,HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
