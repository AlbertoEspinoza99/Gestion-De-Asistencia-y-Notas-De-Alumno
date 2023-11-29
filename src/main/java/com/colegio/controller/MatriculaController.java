package com.colegio.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Alumno;
import com.colegio.entity.Curso;
import com.colegio.entity.Matricula_alumno_has_curso;
import com.colegio.entity.Matricula_alumno_has_cursoPK;
import com.colegio.services.lmpl.AlumnoService;
import com.colegio.services.lmpl.CursoService;
import com.colegio.services.lmpl.MatriculaService;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

	@Autowired
	private MatriculaService serMatricula;
	
	@Autowired
	private CursoService serCurso;
	
	@Autowired
	private AlumnoService serAlum;
	
	@GetMapping("/alumno/{cod}")
	public ResponseEntity<List<Alumno>> listaAlumnoCurso(@PathVariable("cod")int cod){
		
		List<Alumno> lista=serMatricula.listaAlumnoXcodigoCurso(cod);
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
		
	}
	
	
	
	@PostMapping("/registrar/{codAlumno}")
	public ResponseEntity<Void> registrarMatricula(@PathVariable("codAlumno")int cod,@RequestBody Curso obj){
		
		List<Matricula_alumno_has_curso> lista=serMatricula.listaTodos();
		int valor=0;
		
		for(int i=0;i<lista.size();i++) {
			
			if(lista.get(i).getMatricula().getCodPersona()==cod && lista.get(i).getMatriculacurso().getCodigoCurso()==obj.getCodigoCurso()) {
				
				valor=1;
				
			}
			
		}
		
		if(valor==1) {
			
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			
		}else {
			
			Matricula_alumno_has_cursoPK pk=new Matricula_alumno_has_cursoPK();
			pk.setCodigoAlumno(cod);
			pk.setCodigoCurso(obj.getCodigoCurso());
			
			Alumno a=new Alumno(cod);
			
			Matricula_alumno_has_curso matri=new Matricula_alumno_has_curso();
			matri.setId(pk);
			matri.setMatricula(a);
			matri.setMatriculacurso(obj);
			
			serMatricula.registra(matri);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		
		
	}
	
	
	@GetMapping("/lista/alumno/nomatriculados")
	public ResponseEntity<List<Alumno>> listaAlumno(){
		
		
		List<Alumno> lista=serAlum.listaTodos();
		List<Matricula_alumno_has_curso> lista2=serMatricula.listaTodos();
		
		
		Map<String, Alumno> map=new LinkedHashMap<>();
		
		
		for(int i=0;i<lista.size();i++) {
			System.out.println(lista.get(i).getCodPersona());
			System.out.println(lista2.get(i).getId().getCodigoAlumno());
			
				if(lista.get(i).getCodPersona()!=lista2.get(i).getId().getCodigoAlumno()) {
					System.out.println(lista.get(i).getCodPersona());
					System.out.println(lista2.get(i).getId().getCodigoAlumno());
						
						map.put("valor", lista.get(i));
	
					
				}
				
				
			

		}
		List<Alumno> val=new ArrayList<>(map.values());
	
		
		return new ResponseEntity<>(val,HttpStatus.OK);
		
		
		
		
	}
	
	
	
	@GetMapping("/lista/de/matricula")
	public ResponseEntity<List<Matricula_alumno_has_curso>> listaTodos(){
		
		
		List<Matricula_alumno_has_curso> lista=serMatricula.listaTodos();
		return new ResponseEntity<>(lista,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
}
