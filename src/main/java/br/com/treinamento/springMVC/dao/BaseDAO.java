package br.com.treinamento.springMVC.dao;

import java.util.List;

import br.com.treinamento.springMVC.model.BaseModel;

public interface BaseDAO {

	public void pegaConexao();
		
	public void adiciona(BaseModel model);
	
	public void remove(BaseModel model);
	
	public void altera(BaseModel model);
	
	@SuppressWarnings("rawtypes")
	public List lista();
	
}
