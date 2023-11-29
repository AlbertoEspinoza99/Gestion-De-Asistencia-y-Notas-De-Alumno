package com.colegio.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Alumno;
import com.colegio.entity.Asistencia;
import com.colegio.entity.AsistenciaPK;
import com.colegio.entity.AsistenciaResponse;
import com.colegio.entity.Curso;
import com.colegio.services.lmpl.AsistenciaService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {

	
	@Autowired
	private AsistenciaService serAsisten;
	
	@PostMapping("/registrar")
	@Transactional
	public ResponseEntity<Void> registrarAsistencia(@RequestBody List<AsistenciaResponse> asistencia){
		
		List<Asistencia> lista=serAsisten.listaTodos();
		
		for(int i=0;i<asistencia.size();i++) {
			
			int codA=asistencia.get(i).getCodigoAlumno();
			int codC=asistencia.get(i).getCodigoCurso();
			Boolean estado=asistencia.get(i).isEstado();
			int estad;
			
			AsistenciaPK pk=new AsistenciaPK();
			pk.setCodigoAlumno(codA);
			pk.setCodigoCurso(codC);
			
			Alumno a=new Alumno(codA);
			Curso c=new Curso(codC);
			
			if(estado==true) {
				estad=1;
			}else {
				estad=2;
			}
			
			Asistencia asis=new Asistencia();
			asis.setId(pk);
			asis.setAlumnoasistencia(a);
			asis.setCursoasistencia(c);
			asis.setEstado(estad);
			
			
			serAsisten.registra(asis);
			
		}
		
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/lista/asistencia")
	public ResponseEntity<List<Asistencia>> listasa(){
		
		List<Asistencia> lista=serAsisten.listaTodos();
		
		 return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
	
}
