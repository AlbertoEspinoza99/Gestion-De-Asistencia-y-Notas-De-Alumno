package com.colegio.services;

import java.util.List;

public interface ICRUD<T,Id> {

	T registra(T obj);
	T actualiza(T obj);
	void eliminar(Id cod);
	T buscarPorId(Id cod);
	List<T> listaTodos();
	
	
}
