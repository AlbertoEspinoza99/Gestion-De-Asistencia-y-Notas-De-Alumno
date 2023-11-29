package com.colegio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer>{

	
	@Query("select x from Cargo_has_menu a join a.menucargo x where a.cargomenu.codigoCargo=?1")
	public List<Menu> listaDeMenusPorCargo(int cargo);
	
	
	
}
