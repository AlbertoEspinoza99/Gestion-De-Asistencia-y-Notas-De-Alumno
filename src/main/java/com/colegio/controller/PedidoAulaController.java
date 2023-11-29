package com.colegio.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Articulos_Aula;
import com.colegio.entity.Aula;
import com.colegio.entity.PedidoJson;
import com.colegio.entity.Pedidos_aula_has_articulos;
import com.colegio.entity.Pedidos_aula_has_articulosPK;
import com.colegio.services.lmpl.ArticuloService;
import com.colegio.services.lmpl.AulaService;
import com.colegio.services.lmpl.PedidoAulaService;

@RestController
@RequestMapping("/pedido")
public class PedidoAulaController {

	
	@Autowired
	private PedidoAulaService serPedido;
	
	@Autowired
	private AulaService serAula;
	
	@Autowired
	private ArticuloService serArticul;
	
	@PostMapping("/control/{codAr}/{cantidad}")
	public ResponseEntity<Map<String, String>> controlDeCantidad(@PathVariable("codAr")int cod,@PathVariable("cantidad")int cant){
		
		 Map<String, String> map=new HashMap<>();
		Articulos_Aula obj=serArticul.buscarPorId(cod);
		int cantidadActual=obj.getCant();
		
		if(cant<=0) {
			
			map.put("respuesta", "No colocar numeros menores a 0");
			return new ResponseEntity<>(map,HttpStatus.OK);
		}
		else if(cantidadActual<=0){
			
		  map.put("respuesta", "No hay Stock");
			return new ResponseEntity<>(map,HttpStatus.OK);
			
		}else {
			
            int cantidadfinal=cantidadActual - cant;
			
			if (cantidadfinal <0) {
				
				map.put("respuesta", "Cantidad Excedida");
				return new ResponseEntity<>(map,HttpStatus.OK);
				
			}
			else {
				
				obj.setCant(cantidadfinal);
				serArticul.actualiza(obj);
				
				map.put("respuesta", "Listo");
				return new ResponseEntity<>(map,HttpStatus.OK);
			}
			
		}
	}
	
	
	
	@PostMapping("/registrar/{codAu}")
	public ResponseEntity<Map<String, String>> registrar(@PathVariable("codAu")int cod,@RequestBody List<PedidoJson> lista){
		
	  Aula obj=serAula.buscarPorId(cod);
	  Map<String, String> map=new HashMap<>();
	  
	  
	  
	  List<Articulos_Aula> lista22=serArticul.listaTodos();
	  
	  
	  for(int i=0;i<lista22.size();i++) {
		  
		  if (i < lista.size()) {  
		int  cod1=lista22.get(i).getCodigoArticulos();
	
		
		System.out.println(lista.get(i));
		if(lista.get(i) !=null) {
			
			
			 if(lista22.get(i).getCodigoArticulos()==lista.get(i).getCodArticulos()) {
				  
				  Articulos_Aula obj22=serArticul.buscarPorId(lista22.get(i).getCodigoArticulos());
				  
				  if(lista.get(i).getCantidad()<=0) {
					  
					  map.put("respuesta", "No colocar numeros menores a 0");
						return new ResponseEntity<>(map,HttpStatus.OK);
				  }else if(obj22.getCant()<=0) {
					  
					  map.put("respuesta", "No hay Stock");
					  return new ResponseEntity<>(map,HttpStatus.OK);
					  
				  }else {
					  
					  int cantidadFinal=obj22.getCant()-lista.get(i).getCantidad();
					  
					  if (cantidadFinal <0) {
							
							map.put("respuesta", "Cantidad Excedida");
							return new ResponseEntity<>(map,HttpStatus.OK);
							
						}else {
							
							obj22.setCant(cantidadFinal);
							serArticul.actualiza(obj22);
							
							
						}
					  
					  
				  }
				  
				  
			  }
			 /*else {
				  
				    map.put("respuesta", "No ha ingresado Articulos");
					return new ResponseEntity<>(map,HttpStatus.OK);
				  
			  }*/
			
		}
		
  }
		 
		  
	  }
	  
	  
	  
		for(int i=0;i<lista.size();i++) {
			
			Articulos_Aula obj2=serArticul.buscarPorId(lista.get(i).getCodArticulos());
			
	        if(lista.get(i).getCantidad()>0) {
	        	
	        	Pedidos_aula_has_articulosPK pk=new Pedidos_aula_has_articulosPK();
				pk.setCodigoAula(cod);
				pk.setCodigoArticulos(lista.get(i).getCodArticulos());
				
				Pedidos_aula_has_articulos pedidos=new Pedidos_aula_has_articulos();
				pedidos.setId(pk);
				pedidos.setPedidosaula(obj);
				pedidos.setArticulosaulass(obj2);
				pedidos.setCantidadarticulos(lista.get(i).getCantidad());
						
				serPedido.registra(pedidos);
	        }		
			
		}

		map.put("respuesta", "Registrado con exito");
		return new ResponseEntity<>(map,HttpStatus.OK);
	
		
	}
	
	
	
	
	
	
}
