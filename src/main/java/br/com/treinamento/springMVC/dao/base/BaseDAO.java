package br.com.treinamento.springMVC.dao.base;

import java.util.List;

import br.com.treinamento.springMVC.model.BaseModel;

public interface BaseDAO<T extends BaseModel> {

	public void adiciona(T model);
	
	public void remove(T model);
	
	public void altera(T model);
	
	public List<T> lista();
	
	public T buscaPorId(Long id);
	
}
