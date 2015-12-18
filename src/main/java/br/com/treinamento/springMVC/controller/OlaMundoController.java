package br.com.treinamento.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller /* -- O @Controller serve para o spring identificar que está classe é um controller -- */
public class OlaMundoController {

	@RequestMapping("/olaMundo") /* -- Nome digitado na url que fará a requisição -- */
	public String execute() {
		System.out.println("Executando um controller com o spring MVC!");
		
		// lógica ...
		
		return "ok"; /* -- O "ok" é o nome da jsp que o spring vai exibir quando este método acabar -- */
	}
	
}
