package br.com.treinamento.springMVC.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinamento.springMVC.dao.BaseDAO;
import br.com.treinamento.springMVC.dao.ContaSingletonDAO;
import br.com.treinamento.springMVC.model.Conta;

@Controller
public class ContaController {

	@RequestMapping("/form")
	public String formulario() {
		return "formulario";
	}
	
	// O parametro Conta é populado automaticamente pelo Spring MVC com as informações que estão na tela.
	//	Para que isso ocorra é necessário que o nome dos campos do formulários sejam os mesmos dos 
	//	atributos da classe.
	@RequestMapping("/adicionarConta")
	public String adiciona(Conta conta) {
		System.out.println("Conta adicionada é: " + conta.getDescricao());
		BaseDAO dao = new ContaSingletonDAO();
		dao.adiciona(conta);
		
//		List<Conta> contas = dao.lista();
//		
//		for (Conta c : contas) {
//			System.out.println("-------------");
//			System.out.println("id: " + c.getId());
//			System.out.println("Descrição: " + c.getDescricao());
//			System.out.println("Valor: " + c.getValor());
//			System.out.println("Tipo: " + c.getTipo());
//			System.out.println("Paga: " + c.isPaga());
//			System.out.println("DataPagamento: " + c.getDataPagamento());
//			
//		}
		
		return "conta-adicionada";
	}
	
	@RequestMapping("/listaContas")
	public ModelAndView lista() {
		BaseDAO dao = new ContaSingletonDAO();
		List<Conta> contas = dao.lista();
		
		// Para passar valores do controller para a view pelo spring é necessário passar um objeto 
		//	ModelAndView e não mais uma String.
		// O objeto ModelAndView além de receber o nome da jsp, recebe o objeto com os valores que 
		//	se deseja passar para a view.
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("todasContasXX", contas);
		return mv;
	}
	
}
