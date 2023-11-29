package com.colegio.services.lmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.PedidosAulaRepository;
import com.colegio.entity.Pedidos_aula_has_articulos;

@Service
public class PedidoAulaService extends ICRUDlmpl<Pedidos_aula_has_articulos, Integer>{

	@Autowired
	private PedidosAulaRepository repo;
	
	@Override
	public JpaRepository<Pedidos_aula_has_articulos, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

}
