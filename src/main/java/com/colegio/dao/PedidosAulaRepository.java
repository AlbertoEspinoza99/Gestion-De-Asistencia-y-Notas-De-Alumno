package com.colegio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Pedidos_aula_has_articulos;
@Repository
public interface PedidosAulaRepository extends JpaRepository<Pedidos_aula_has_articulos, Integer>{

}
