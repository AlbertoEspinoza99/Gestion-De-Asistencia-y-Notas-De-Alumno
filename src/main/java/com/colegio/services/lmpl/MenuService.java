package com.colegio.services.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.colegio.dao.MenuRepository;
import com.colegio.entity.Menu;
@Service
public class MenuService extends ICRUDlmpl<Menu, Integer>{

	@Autowired
	private MenuRepository repo; 
	
	@Override
	public JpaRepository<Menu, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

	
	public List<Menu> listaMenuPORcargo(int cod){
	   return	repo.listaDeMenusPorCargo(cod);
	}
	
}
