package com.colegio.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.colegio.dao.AlumnoRepository;
import com.colegio.entity.Alumno;
import com.colegio.services.IUploadFilesService;
import com.colegio.services.lmpl.AlumnoService;

@RestController
@RequestMapping("/upload")
public class ImagenesController {

	@Autowired
	private IUploadFilesService serviUpload;
	
	@Autowired
    private AlumnoService serAlu;
	
	@Autowired
	private AlumnoRepository repoAlumno;
	
	@PostMapping("/imagen")
	public ResponseEntity<String> uploadPic(@RequestParam("file") MultipartFile file,Authentication auth) throws Exception{
		
		// HTTP 400 significa "Bad Request" (Solicitud incorrecta)
		Alumno obj=repoAlumno.findByUsername(auth.getName()).orElseThrow(()-> new UsernameNotFoundException("usuario no encontrado"));
		String ruta=serviUpload.handleFileUpload(file);
		
		if(ruta=="error1") {
			return new ResponseEntity<>("archivo maximo de 5MB",HttpStatus.OK);
		}else if(ruta=="error2") {
			return new ResponseEntity<>("solo archivos .png , .jpg , .jpeg",HttpStatus.OK);
		}else {
		
			obj.setImagen(ruta);
			serAlu.actualiza(obj);
			return new ResponseEntity<>("exito",HttpStatus.OK);
		}
		

	}
	
	
	///ESTO ME PERMITIRA TRAER LA IMAGEN EN ESTE CASO , SIN SUBIR EL PROYECTO A LA NUBE
	
	
	@GetMapping("/obtener/imagen/{image}")
	public ResponseEntity<Resource> getImagen(@PathVariable("image")String imagenName)throws IOException{
		
		Path imagePath = Paths.get("src/main/resources/picture/" + imagenName);
		String fileExtension=imagenName.substring(imagenName.lastIndexOf("."));
		String contentType;
		System.out.println(fileExtension);
		
		if(fileExtension.equals(".jpg") || fileExtension.equals(".jpeg")) {
			
			contentType=MediaType.IMAGE_JPEG_VALUE;
		}else if(fileExtension.equals(".png")) {
			
			contentType=MediaType.IMAGE_PNG_VALUE;
		}else {
			   return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
		}
		
		Resource resource=new UrlResource(imagePath.toUri());
		
		if(resource.exists() && resource.isReadable()) {
			return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .body(resource); 
		}
		 else {
		        
		        return ResponseEntity.notFound().build();
		    }
		
	}
	
	
	
	
	
	
	
}
