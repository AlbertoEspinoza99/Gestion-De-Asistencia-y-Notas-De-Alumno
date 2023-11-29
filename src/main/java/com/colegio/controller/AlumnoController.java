package com.colegio.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.dao.AlumnoRepository;
import com.colegio.dao.CargoRepository;
import com.colegio.dao.PersonaRepository;
import com.colegio.entity.Alumno;
import com.colegio.entity.Asistencia;
import com.colegio.entity.Cargo;
import com.colegio.entity.Curso;
import com.colegio.entity.Estado;
import com.colegio.entity.Examen;
import com.colegio.entity.Nota;
import com.colegio.entity.NotaPK;
import com.colegio.entity.Persona;
import com.colegio.entity.Preguntas;
import com.colegio.services.lmpl.AlumnoService;
import com.colegio.services.lmpl.AsistenciaService;
import com.colegio.services.lmpl.EmailServiceImpl;
import com.colegio.services.lmpl.ExamenService;
import com.colegio.services.lmpl.MatriculaService;
import com.colegio.services.lmpl.NotaServiceImpl;
import com.colegio.services.lmpl.PreguntaService;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/ALUMNO")
public class AlumnoController {

	@Autowired
    private AlumnoService serAlu;
	
	@Autowired
	private AlumnoRepository repoAlumno;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CargoRepository cargoRepo;
	
	 @Autowired
    private PersonaRepository repo;
	
	@Autowired 
    private MatriculaService serMatricula;
	 
	@Autowired 
	private ExamenService serExamen;
	 
	@Autowired
	private PreguntaService serPregunta;
	
	@Autowired
	private NotaServiceImpl serNota;
	
	@Autowired
	private AsistenciaService serAsistencia;
	
	@Autowired
	private EmailServiceImpl senderService;
	
	
	    @GetMapping("/admin")
	    public String welcome()
	    {
		  	 
	        return "Welcome alumno";
	    }
	
	 @GetMapping("/lista")
	 public ResponseEntity<List<Alumno>> lista(){
		 
		 List<Alumno> lista=serAlu.listaTodos();
		 
		 return new ResponseEntity<>(lista,HttpStatus.OK);
	 }
	    
	    
	    
    @GetMapping("/buscarPorId/{cod}")
    public ResponseEntity<Alumno> buscar(@PathVariable("cod")int cod) throws NotFoundException{
    	
    	Alumno a=serAlu.buscarPorId(cod);
    	
        if (a==null) {
			
			throw new NotFoundException();
			
		}else {
			
			a=serAlu.buscarPorId(cod);
			
		}
		return new ResponseEntity<>(a,HttpStatus.OK);
    }
	
	    
	    
	    
	@PutMapping("/actualizar")
	public ResponseEntity<Alumno> actualizar(@RequestBody Alumno obj) throws NotFoundException{
		
		Alumno a=serAlu.buscarPorId(obj.getCodPersona());
		
		if (a==null) {
			
			throw new NotFoundException();
			
		}else {
			
			senderService.sendSimpleEmail(obj.getCorreo(),
					"Tu Nueva Contraseña",
					"Contraseña : "+obj.getPassword());
			
			
			String code=passwordEncoder.encode(obj.getPassword());
			Cargo userRole = cargoRepo.findByAuthority("ALUMNO").get();
			
			Set<Cargo> authorities = new HashSet<>();
			
			 authorities.add(userRole);
			 Estado e=new Estado(1);
			// LocalDate fechaActual = LocalDate.now();
			    obj.setPassword(code);
			    obj.setCargopersona(authorities);
		    //    obj.setFechaRegistro(fechaActual);
		        obj.setAlumnoestado(e);
			
			a=serAlu.actualiza(obj);
			
		}
		return new ResponseEntity<>(a,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/eliminar/{cod}")
	public ResponseEntity<Void> eliminar(@PathVariable("cod")int codd) throws NotFoundException{
		
		Alumno a=serAlu.buscarPorId(codd);
		
          if(a==null) {
			
			throw new NotFoundException();
		}else {
			
			serAlu.eliminar(codd);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	//NUEVO
	
    @GetMapping("/validar/cambioContrasena/{pass}")
    public ResponseEntity<Map<String, String>> validar(Authentication auth,@PathVariable("pass")String password){
    	
    	Map<String, String> map=new HashMap<>();
    	String usuario=auth.getName();
    	
    	Persona obj=repo.findByUsername(usuario).orElseThrow(()-> new UsernameNotFoundException("usuario no reconocido"));
    	
    	String contrasenaencriptada=obj.getPassword();
    	
    	boolean esverdad=passwordEncoder.matches(password, contrasenaencriptada);
    	
    	if(esverdad==false) {
    		
    		map.put("respuesta", "Error");
    		return new ResponseEntity<>(map,HttpStatus.FAILED_DEPENDENCY);
    	}
    	else {
    		
    		map.put("respuesta", "OK");
    		return new ResponseEntity<>(map,HttpStatus.OK);
    	}
    }
    
	
    @GetMapping("/cambiar/contrasena/{pass}")
    public ResponseEntity<Map<String, String>> cambiarcontrasena(Authentication auth,@PathVariable("pass")String password){
    	
    	String userr=auth.getName();
    	Persona obj=repo.findByUsername(userr).orElseThrow(()->new UsernameNotFoundException("usuario no reconocido"));
    	
    	String contrasenuevo=passwordEncoder.encode(password);
    	
    	obj.setPassword(contrasenuevo);
    	
    	repo.save(obj);
    	
    	Map<String, String> map=new HashMap<>();
    	map.put("respuesta", "actualizado");
    	return new ResponseEntity<>(map,HttpStatus.OK);
    }
    
	
	@GetMapping("/cursos/PorAlumno")
	public ResponseEntity<List<Curso>> listaCursos(Authentication auth){
		
		String nombre=auth.getName();
		
		List<Curso> lista=serMatricula.listaDeCursosPorAlumno(nombre);
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}
    
    
    
    
    @GetMapping("/listaExamen/Curso/{codCurso}")
    public ResponseEntity<List<Examen>> listaExamen(@PathVariable("codCurso")int cod){
    	
    	List<Examen> lista=serExamen.listaExamenPorCurso(cod);
    	List<Examen> listaFinal=new ArrayList<>();
    	
    	for(int i=0;i<lista.size();i++) {
    		
    		if(lista.get(i).getActivar()==1) {
    			
    			listaFinal.add(lista.get(i));
    			
    		}
    		
    	}
    	
    	
    	return new ResponseEntity<>(listaFinal,HttpStatus.OK);
    }
    
    
    @GetMapping("/listaPregunta/{codExamen}")
    public ResponseEntity<Page<Preguntas>> pagePreguntas(@PathVariable("codExamen")int cod,Pageable pageable){
    	
    	Page<Preguntas> pages=serPregunta.listaDePreguntasPorCodExamen(cod, pageable);
    	
    	return new ResponseEntity<>(pages,HttpStatus.OK);
    	
    }
    
	
    
    @PostMapping("/RegistrarNota")
    public ResponseEntity<Map<String, String>> registrarNota(@RequestBody List<Preguntas> objLista,Authentication auth){
    	
    	Double puntos=0.00;
    	Double nota=0.00;
        Alumno objALUMNO=repoAlumno.findByUsername(auth.getName()).orElseThrow(()->new UsernameNotFoundException("usuario no reconocido"));
        Examen objExamen = null;
    	Map<String, String> map=new HashMap<>();
        
    	
    	
    	for(int i=0;i<objLista.size();i++) {
    		
    		Preguntas objetoSinCambio=serPregunta.buscarPorId(objLista.get(i).getCodigoPregunta());
    		objExamen=serExamen.buscarPorId(objetoSinCambio.getPreguntaexamen().getCodigoExamen());
    		List<Preguntas> listapreguntas=serPregunta.listaPreguntaPorExamen(objLista.get(i).getPreguntaexamen().getCodigoExamen());
    		if(objetoSinCambio.getRespuesta().equals(objLista.get(i).getRespuesta())) {
    			
    			double puntosExamen = objExamen.getPuntosExamen();
    			int cantidadPreguntas = listapreguntas.size();
    			puntos=puntosExamen/cantidadPreguntas;
    			nota+=puntos;
    			
    		 }
    		
    	}
    	
    	NotaPK pk=new NotaPK();
    	pk.setCodigoAlumno(objALUMNO.getCodPersona());
    	pk.setCodigoExamen(objExamen.getCodigoExamen());
    	
    	Nota has=new Nota();
    	has.setId(pk);
    	has.setAlumnonota(objALUMNO);
    	has.setExamennota(objExamen);
    	has.setNotaAlumno(nota);
    	
    	serNota.registra(has);
    	map.put("respuesta", "Tu nota es "+nota);
    	
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
	@GetMapping("/lista/ExamenRealizado/{codExamen}")
	public ResponseEntity<Map<String, String>> listaExamenRealizados(@PathVariable("codExamen")int codd,Authentication auth){
		
		Alumno objALUMNO=repoAlumno.findByUsername(auth.getName()).orElseThrow(()->new UsernameNotFoundException("usuario no reconocido"));
		List<Nota> lista=serNota.listaTodos();
		Map<String, String> map=new HashMap<>();
		
		for(int i=0;i<lista.size();i++) {
			
			if(lista.get(i).getExamennota().getCodigoExamen().equals(codd) && lista.get(i).getAlumnonota().getCodPersona().equals(objALUMNO.getCodPersona())){
			
				map.put("respuesta", "existe");
				return new ResponseEntity<>(map,HttpStatus.OK);
			}
			
		}
		
		map.put("respuesta", "no");
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
	
	@GetMapping("/listaDeAsistencia")
	public ResponseEntity<List<Asistencia>> listaAsistencia(Authentication auth){
		
		Alumno objALUMNO=repoAlumno.findByUsername(auth.getName()).orElseThrow(()->new UsernameNotFoundException("usuario no reconocido"));
		List<Asistencia> lista=serAsistencia.listaAsistenciaPorCodAlumno(objALUMNO.getCodPersona());
		return new ResponseEntity<>(lista,HttpStatus.OK);
		
	}
	
	//serNota
	@GetMapping("/listaDeNotasPoralumno")
	public ResponseEntity<List<Nota>> listaNotaAlumno(Authentication auth){
		
		Alumno objALUMNO=repoAlumno.findByUsername(auth.getName()).orElseThrow(()->new UsernameNotFoundException("usuario no reconocido"));
		List<Nota> lista=serNota.listaNotaPorAlumno(objALUMNO.getCodPersona());
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/obtenerPorAuthentica/alumnoo")
	public ResponseEntity<Map<String, Object>> obtenerauth(Authentication auth){
		
		Map<String, Object> map=new HashMap<>();
		Alumno objALUMNO=repoAlumno.findByUsername(auth.getName()).orElse(null);
		System.out.println(auth.getName());
		if(objALUMNO==null) {
			map.put("respuesta", null);
			System.out.println("null");
			return new ResponseEntity<>(map,HttpStatus.OK);
		}
		map.put("respuesta", objALUMNO);
		System.out.println("valor");
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
	
	
	//codigo para el pdf de notas
	
	@GetMapping("/pdfNotas")
	public ResponseEntity<List<Nota>> listapdf(){
		
		List<Nota> lista=serNota.listaTodos();
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
	
	
}
