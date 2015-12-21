package br.com.treinamento.springMVC.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinamento.springMVC.dao.ContaDAO;
import br.com.treinamento.springMVC.model.Conta;

@Controller
public class ContaController {
	
	// Com tem muitas instanciação do ContaDAO, vamos colocar ele como um atributo da classe.
	private ContaDAO dao;
	
	/* Para fazer o Spring injetar a dependência, por exemplo o ContaDAO, indicamos para ele com
		a anotação @Autowired o método construtor que queremos que ocorrá a injeção.
	   Agora o Spring sabe que ao instanciar está classe precisa passar o ContaDAO no construtor. */
	@Autowired
	public ContaController(ContaDAO dao) {
		// Uma boa prática é receber o ContaDAO e não instancia-lo e com isso diminuiremos o aclopamento. 
		// dao = new ContaDAO();
		this.dao = dao;
	}

	@RequestMapping("/form")
	public String formulario() {
		return "formulario";
	}
	
	/* O parametro Conta é populado automaticamente pelo Spring MVC com as informações que estão na tela.
		Para que isso ocorra é necessário que o nome dos campos do formulários sejam os mesmos dos 
		atributos da classe. */
	@RequestMapping("/adicionarConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {
		// BindingResult é o parâmetro em que o Spring MVC guarda o resultado das validações.
		System.out.println("hasErrors: " + result.hasErrors());
		if (result.hasErrors()) {
			return "formulario";
		}
		
		/* Podemos validar se um dado foi preenchida da seguinte maneira, mas a partir do java 6 
		 * 	a Oracle criou a especificação do Bean Validation que serve para realizar a validação
		 * 	dos dados de nossas classes, baseada em anotações.
		 * As validações são colocadas nas entidades da aplicação e no controller é colocada a 
		 * 	anotação @Valid para indicar os objetos que possuem anotações de validação.
		 * 
		if (conta.getDescricao() == null || conta.getDescricao().equals("")) {
			return "formulario";
		}
		*/
		
		System.out.println("Conta adicionada é: " + conta.getDescricao());
		//ContaDAO dao = new ContaDAO();
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
		//ContaDAO dao = new ContaDAO();
		List<Conta> contas = dao.lista();
		
		// Para passar valores do controller para a view pelo spring é necessário passar um objeto 
		//	ModelAndView e não mais uma String.
		// O objeto ModelAndView além de receber o nome da jsp, recebe o objeto com os valores que 
		//	se deseja passar para a view.
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("todasContasXX", contas);
		return mv;
	}
	
	@RequestMapping("/pagaConta")
	public String paga(Conta conta) {
		//ContaDAO dao = new ContaDAO();
		dao.paga(conta.getId());
		return "redirect:listaContas";
	}
	
	/*
	 * Como este método para pagar a conta está sendo chamado por Ajax, não é necessário retornar nada
	 * 	pois no xhtml está apenas exibindo uma alert sem atualizar a tela, então não é necessário fazer
	 * 	o "redirect:listaContas" pois o mesmo fará um trabalho grande no servidor e este trabalho não 
	 * 	é necessário.
	 */
	@RequestMapping("/pagaContaAjax")
	public void pagaAjax(Conta conta, HttpServletResponse response) {
		//ContaDAO dao = new ContaDAO();
		dao.paga(conta.getId());
		// Como este método não está retornando nada para a tela agora, é necessário informar ao jquery
		//	que a operação ocorrendo de maneira correta, para isso é necessário setar o status da resposta.
		response.setStatus(200);
		
		/* Status Code
		 * SUCCESS (2xx) 
		 * 200 - OK
		 * 201 - Created
		 * 202 - Accepted
		 * 203 - Partial Information
		 * 204 - No Response
		 * 
		 * REDIRECTION (3xx)
		 * 301 - Moved
		 * 302 - Found
		 * 303 - Method
		 * 304 - Not Modified 
		 * 
		 * ERROR (4xx, 5xx)
		 * 400 - Bad request
		 * 401 - Unauthorized
		 * 402 - PaymentRequired
		 * 403 - Forbidden 
		 * 404 - Not Found
		 * 500 - Internal Error
		 * 501 - Not implemented
		 * 502 - Service temporarily overloaded (TO BE DISCUSSED)
		 * 503 - Gateway timeout (TO BE DISCUSSED) 
		 */
	}
	
	@RequestMapping("/removeConta")
	public String remove(Conta conta) {
		//ContaDAO dao = new ContaDAO();
		dao.remove(conta);
		return "redirect:listaContas";
	}
	
}
