package com.colegio.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.dao.CargoRepository;
import com.colegio.dao.EmpleadoRepository;
import com.colegio.dao.PersonaRepository;
import com.colegio.entity.Alumno;
import com.colegio.entity.Cargo;
import com.colegio.entity.Empleado;
import com.colegio.entity.Estado;
import com.colegio.entity.Persona;
import com.colegio.services.lmpl.EmpleadoService;


@RestController
@RequestMapping("/PROFESOR")
public class ProfesorController {

    @Autowired
	private PersonaRepository repo;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
	private CargoRepository cargoRepo;
    
    @Autowired
    private EmpleadoRepository repoempleado;
    
    @Autowired
    private EmpleadoService serEmpleado;
	
    @GetMapping("/admin")
    public String welcome()
    {
	  	 
        return "Welcome profesor";
    }
	
    
    @GetMapping("/validar/cambioContrasena/{user}/{pass}")
    public ResponseEntity<Map<String, String>> validar(@PathVariable("user")String usuario,@PathVariable("pass")String password){
    	
    	Map<String, String> map=new HashMap<>();
    	
    	Persona obj=repo.findByUsername(usuario).orElseThrow(()-> new UsernameNotFoundException("usuario no reconocido"));
    	
    	String contrasenaencriptada=obj.getPassword();
    	
    	boolean esverdad=encoder.matches(password, contrasenaencriptada);
    	
    	if(esverdad==false) {
    		
    		map.put("respuesta", "Error");
    		return new ResponseEntity<>(map,HttpStatus.FAILED_DEPENDENCY);
    	}
    	else {
    		
    		map.put("respuesta", "OK");
    		return new ResponseEntity<>(map,HttpStatus.OK);
    	}
    }
    
	
    
    @GetMapping("/cambiar/contrasena/{user}/{pass}")
    public ResponseEntity<Map<String, String>> cambiarcontrasena(@PathVariable("user")String userr,@PathVariable("pass")String password){
    	
    	Persona obj=repo.findByUsername(userr).orElseThrow(()->new UsernameNotFoundException("usuario no reconocido"));
    	
    	String contrasenuevo=encoder.encode(password);
    	
    	obj.setPassword(contrasenuevo);
    	
    	repo.save(obj);
    	
    	Map<String, String> map=new HashMap<>();
    	map.put("respuesta", "actualizado");
    	return new ResponseEntity<>(map,HttpStatus.OK);
    }
    
    
    @GetMapping("/obtener/porusu/{user}")
    public ResponseEntity<Persona> obteneremplea(@PathVariable("user")String userr){
    	
    	//Empleado obj=repoempleado.findByUsername(userr).orElseThrow(()->new UsernameNotFoundException("usuario no reconocido"));
    	Persona obj=repo.findByUsername(userr).orElseThrow(()->new UsernameNotFoundException("usuario no reconocido"));
    	
    	return new ResponseEntity<>(obj,HttpStatus.OK);
    }
    
    
    /* ARREGLAR
     @GetMapping("/lista")
	 public ResponseEntity<List<Empleado>> lista(){
		 
		 List<Empleado> lista=repoempleado.findAll();
		 
		 return new ResponseEntity<>(lista,HttpStatus.OK);
	 }
	    */
	    
	    
    @GetMapping("/buscarPorId/{cod}")
    public ResponseEntity<Empleado> buscar(@PathVariable("cod")int cod) throws NotFoundException{
    	
    	Empleado a=repoempleado.findById(cod).orElseThrow(null);
    	
        if (a==null) {
			
			throw new NotFoundException();
			
		}else {
			
			a=repoempleado.findById(cod).orElseThrow(null);
			
		}
		return new ResponseEntity<>(a,HttpStatus.OK);
    }
	
    
    
    @PutMapping("/actualizar")
	public ResponseEntity<Empleado> actualizar(@RequestBody Empleado obj) throws NotFoundException{
		
    	Empleado a=repoempleado.findById(obj.getCodPersona()).orElseThrow(null);
		
    	
		if (a==null) {
			System.out.println("entro78");
			throw new NotFoundException();
			
		}else {
			
			//String code=encoder.encode(obj.getPassword());
		///	Cargo userRole = cargoRepo.findByAuthority("PROFESOR").get();
			
		////	Set<Cargo> authorities = new HashSet<>();
			
		///	 authorities.add(userRole);
			 Estado e=new Estado(1);
			// LocalDate fechaActual = LocalDate.now();
			 //   obj.setPassword(code);
			///    obj.setCargopersona(authorities);
		    //    obj.setFechaRegistro(fechaActual);
		        obj.setEmpleadoestado(e);
			
			a=repoempleado.save(obj);
			
		}
		return new ResponseEntity<>(a,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/eliminar/{cod}")
	public ResponseEntity<Void> eliminar(@PathVariable("cod")int codd) throws NotFoundException{
		
		Empleado a=repoempleado.findById(codd).orElseThrow(null);
		
          if(a==null) {
			
			throw new NotFoundException();
		}else {
			
			repoempleado.deleteById(codd);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
    
	////----->
	
	@GetMapping("/authenticacion/profesor")
	public ResponseEntity<Empleado> obtenerprofesor(Authentication auth){
		
		String userr=auth.getName();
		Empleado obj=repoempleado.findByUsername(userr).orElseThrow(()->new UsernameNotFoundException("Usuario NO encontrado"));
		
		return new ResponseEntity<>(obj,HttpStatus.OK);
		
	}
	
	@PutMapping("/actualizar/prosor")
	public ResponseEntity<Void> actu(@RequestBody Empleado obj) throws NotFoundException{
		
		Empleado a=repoempleado.findById(obj.getCodPersona()).orElseThrow(null);
		  if(a==null) {
				
				throw new NotFoundException();
			}else {
				serEmpleado.actualiza(obj);
			}
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/lista/prosor")
	public ResponseEntity<List<Persona>> listaProsor(){
		
		Cargo c=new Cargo(3);
		List<Persona> lista=repoempleado.findPersonasByCargo(c);
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}
	
    
 
    
}
