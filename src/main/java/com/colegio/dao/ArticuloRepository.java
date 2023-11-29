package com.colegio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Articulos_Aula;
@Repository
public interface ArticuloRepository extends JpaRepository<Articulos_Aula, Integer>{

}
