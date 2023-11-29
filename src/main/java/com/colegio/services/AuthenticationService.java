package com.colegio.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.AuthenticationException;

import com.colegio.dao.AlumnoRepository;
import com.colegio.dao.CargoRepository;
import com.colegio.dao.EmpleadoRepository;
import com.colegio.dao.PersonaRepository;
import com.colegio.entity.Alumno;
import com.colegio.entity.Cargo;
import com.colegio.entity.Empleado;
import com.colegio.entity.Estado;
import com.colegio.entity.LoginResponseDTO;
import com.colegio.entity.Persona;
import com.colegio.services.lmpl.PersonaService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@SessionAttributes({"codcargo"})
@Service
@Transactional
public class AuthenticationService {

	
	@Autowired
	private PersonaRepository repoPersona;
	
	@Autowired
	private CargoRepository cargoRepo;
	
	@Autowired
	private EmpleadoRepository repoEmpleado;
	
	@Autowired
	private AlumnoRepository repoAlumno;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private PersonaService servicePersona;
	
	
	public Empleado  registerUser(String username , String password) {
		
		String encodedPassword = passwordEncoder.encode(password);
		Cargo userRole = cargoRepo.findByAuthority("PROFESOR").get();
		
		 Set<Cargo> authorities = new HashSet<>();

	    authorities.add(userRole);

	    
		
		return repoEmpleado.save(new Empleado(0, "nombre", "paterno", "materno", username, 0, encodedPassword, "correo", 123, null, null, authorities, null, null));
		
	}
	
	
	public Empleado  registerAdmin(@RequestBody Empleado obj) {
		
		String encodedPassword = passwordEncoder.encode(obj.getPassword());
		Cargo userRole = cargoRepo.findByAuthority("ADMIN").get();
		
		 Set<Cargo> authorities = new HashSet<>();

	    authorities.add(userRole);

	    obj.setPassword(encodedPassword);
	    obj.setCargopersona(authorities);
	    

		return  repoEmpleado.save(obj);
		
	}
	
	
	public Empleado  registerProfesor(@RequestBody Empleado obj) {
			
			String encodedPassword = passwordEncoder.encode(obj.getPassword());
			Cargo userRole = cargoRepo.findByAuthority("PROFESOR").get();
			
			 Set<Cargo> authorities = new HashSet<>();
	
		    authorities.add(userRole);
		    LocalDate fechaActual = LocalDate.now();
		    
		    Estado e=new Estado(1);
		    
		    List<Persona> lista=servicePersona.listaTodos();
		    
		    for(int i=0;i<lista.size();i++) {
		    	
		    	if(lista.get(i).getUsername().equals(obj.getUsername())) {
		    		
		    		return null;
		    		
		    	}
		    	
		    }
	
		    obj.setPassword(encodedPassword);
		    obj.setCargopersona(authorities);
		    obj.setFechaRegistro(fechaActual);
		    obj.setEmpleadoestado(e);;
	
			return  repoEmpleado.save(obj);
			
		}
	
	
	
	
	
	public Alumno  registerAlumno(@RequestBody Alumno obj) {
		
		String encodedPassword = passwordEncoder.encode(obj.getPassword());
		Cargo userRole = cargoRepo.findByAuthority("ALUMNO").get();
		
		 Set<Cargo> authorities = new HashSet<>();

	    authorities.add(userRole);
	    LocalDate fechaActual = LocalDate.now();
	    
	    Estado e=new Estado(1);
	    
	    List<Persona> lista=servicePersona.listaTodos();
	    
	    for(int i=0;i<lista.size();i++) {
	    	
	    	if(lista.get(i).getUsername().equals(obj.getUsername())) {
	    		
	    		return null;
	    		
	    	}
	    	
	    }
	    
	    
	    
	    obj.setPassword(encodedPassword);
	    obj.setCargopersona(authorities);
        obj.setFechaRegistro(fechaActual);
        obj.setAlumnoestado(e);
        
		return  repoAlumno.save(obj);
		
	}
	
	
	public LoginResponseDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);
            
            //CODIGO NUEVO PARA OBTENER EL CODIGO DE ROL
            Set<Cargo> cargo= repoPersona.findByUsername(username).get().getCargopersona();
            String codigoCargo = cargo.stream()
                    .map(Cargo::getCodigoCargo)  
                    .findFirst() 
                    .map(String::valueOf)
                    .orElse("N/A");
            System.out.println(codigoCargo);
            
            //FIN CODIGO NUEVO PARA OBTENER EL CODIGO DE ROL
            
            return new LoginResponseDTO(repoPersona.findByUsername(username).get().getUsername(), token,codigoCargo);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, null,null);
        }
    }
	
	
	
}
