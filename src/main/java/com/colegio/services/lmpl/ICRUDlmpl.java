package com.colegio.services.lmpl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegio.services.ICRUD;

public abstract  class ICRUDlmpl<T,Id> implements ICRUD<T, Id>{

	public abstract  JpaRepository<T, Id> getRepository();
	
	@Override
	public T registra(T obj) {
		// TODO Auto-generated method stub
		return getRepository().save(obj);
	}

	@Override
	public T actualiza(T obj) {
		// TODO Auto-generated method stub
		return getRepository().save(obj);
	}

	@Override
	public void eliminar(Id cod) {
		getRepository().deleteById(cod);
		
	}

	@Override
	public T buscarPorId(Id cod) {
		// TODO Auto-generated method stub
		return getRepository().findById(cod).orElse(null);
	}

	@Override
	public List<T> listaTodos() {
		// TODO Auto-generated method stub
		return getRepository().findAll();
	}

}
