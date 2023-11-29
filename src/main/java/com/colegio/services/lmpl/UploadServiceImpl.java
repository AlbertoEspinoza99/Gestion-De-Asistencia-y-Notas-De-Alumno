package com.colegio.services.lmpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.colegio.services.IUploadFilesService;

@Service
public class UploadServiceImpl implements IUploadFilesService{

	
	
	@Override
	public String handleFileUpload(MultipartFile file) throws Exception {

		  try {
			 //esto nos generara un id unico y aleatorio
			  String fileName= UUID.randomUUID().toString();
			  //transformar el archivo a bytes
			  byte[] bytes=file.getBytes();
			  //ver la extencion del nombre
			  String fileOriginalName=file.getOriginalFilename();
			  
			  //aqui veremos el tamano del archivo (para controlar el tamano)
			  
			  long fileSize=file.getSize();
			  long maxFilesize=5 * 1024 * 1024;
			  
			  if(fileSize > maxFilesize) {
				//  return "File size must be less then or equal 5MG";
				  return "error1";
			  }
			  
			  if(!fileOriginalName.endsWith(".jpg") && 
				 !fileOriginalName.endsWith(".jpeg")&&
				 !fileOriginalName.endsWith(".png")) 
			  {
				  
				  //return "Only images jpg , jpeg , png";
				  return "error2";
			  }
			  //como vamos a cambiar el nombre no se colocara la axtencion y eso
			  //lo arreglaremos
			  
			  //capturamos el substring --> resultado : .pdf
			  String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
			  //nuevo nombre del archivo
			  String newFileName=fileName + fileExtension;
			  
			  //pondremos donde se va a alojar
			  File folder=new File("src/main/resources/picture"); 
			 // File folder=new File("src/main/resources/static"); 
			  
			  
			  if(!folder.exists()) {
				  folder.mkdir();
			  }
			  
			  Path path= Paths.get("src/main/resources/picture/"+newFileName);
			 // Path path= Paths.get("src/main/resources/static/"+newFileName);
			  
			  //con esto obtengo la ruta del arvhivo
			   //String filePath = path.toString();
			  
			  Files.write(path, bytes);
			  
			  
			  return newFileName;
			  
			  
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
	}

}
