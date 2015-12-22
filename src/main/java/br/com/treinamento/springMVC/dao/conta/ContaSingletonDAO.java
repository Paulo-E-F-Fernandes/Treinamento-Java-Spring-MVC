package br.com.treinamento.springMVC.dao.conta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.treinamento.springMVC.dao.base.BaseSingletonDAO;
import br.com.treinamento.springMVC.model.Conta;

@Component("contaSingletonDAO")
public class ContaSingletonDAO extends BaseSingletonDAO<Conta> implements ContaBaseDAO {

	private static List<Conta> listaContas;
	
	public ContaSingletonDAO() {
		if (listaContas == null) {
			ContaSingletonDAO.listaContas = new ArrayList<Conta>();
		}
	}

	@Override
	public void remove(Conta model) {
		listaContas.remove(model);
	}

	@Override
	public void altera(Conta model) {
		listaContas.set(listaContas.indexOf(model), model);
	}

	@Override
	public List<Conta> lista() {
		return listaContas;
	}

	@Override
	public Conta buscaPorId(Long id) {
		Conta conta = new Conta();
		conta.setId(id);
		return listaContas.get(listaContas.indexOf(conta));
	}

	@Override
	protected void adicionaRegistro(Conta model) {
		listaContas.add(model);
	}

	@Override
	public void paga(Long id) {
		Conta conta = buscaPorId(id);
		conta.setPaga(true);
		conta.setDataPagamento(Calendar.getInstance());
		listaContas.set(listaContas.indexOf(conta), conta);
	}
	
}
