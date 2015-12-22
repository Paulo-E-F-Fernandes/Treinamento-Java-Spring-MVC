package br.com.treinamento.springMVC.dao.conta;

import br.com.treinamento.springMVC.dao.base.BaseDAO;
import br.com.treinamento.springMVC.model.Conta;

public interface ContaBaseDAO extends BaseDAO<Conta> {

	public void paga(Long id);
	
}
