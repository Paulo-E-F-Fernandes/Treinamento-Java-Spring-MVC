package br.com.treinamento.springMVC.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.treinamento.springMVC.model.BaseModel;
import br.com.treinamento.springMVC.model.Conta;

public class ContaSingletonDAO implements BaseDAO {

	private static List<Conta> connection;
	private static long id = 1;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void pegaConexao() {
		if (connection == null) {
			connection = new ArrayList();
		}
	}

	@Override
	public void adiciona(BaseModel model) {
		this.pegaConexao();
		Conta conta = (Conta) model;
		conta.setId(this.generateId());
		connection.add(conta);
	}

	@Override
	public void remove(BaseModel model) {
		this.pegaConexao();
		Conta conta = (Conta) model;
		connection.remove(conta);
	}

	@Override
	public void altera(BaseModel model) {
		this.pegaConexao();
		Conta conta = (Conta) model;
		connection.set(connection.indexOf(conta), conta);
	}
	
	@Override
	public List<Conta> lista() {
		return connection;
	}
	
	public Conta buscaPorId(Long id) {
		Conta conta = new Conta();
		conta.setId(id);
		return connection.get(connection.indexOf(conta));
	}

	public void paga(Long id) {
		Conta conta = new Conta();
		conta.setId(id);
		int index = connection.indexOf(conta);
		Conta contaAtualizada = connection.get(index);
		contaAtualizada.setPaga(true);
		contaAtualizada.setDataPagamento(Calendar.getInstance());
		connection.set(index, contaAtualizada);
	}
	
	private long generateId() {
		return id++;
	}

}
