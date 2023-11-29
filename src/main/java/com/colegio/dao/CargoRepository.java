package com.colegio.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

	Optional<Cargo> findByAuthority(String nombreCargo);
	
}
