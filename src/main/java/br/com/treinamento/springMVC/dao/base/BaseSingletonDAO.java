package br.com.treinamento.springMVC.dao.base;

import br.com.treinamento.springMVC.model.BaseModel;

public abstract class BaseSingletonDAO<E extends BaseModel> implements BaseDAO<E> {
	
	protected static long id = 1;
	
	// Template Method
	@Override
	public void adiciona(E model) {
		model.setId(this.generateId());
		adicionaRegistro(model);
	}
	
	protected abstract void adicionaRegistro(E model);
	
	protected long generateId() {
		return id++;
	}

}
