package br.com.treinamento.springMVC.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.treinamento.springMVC.dao.UsuarioDAO;
import br.com.treinamento.springMVC.model.Usuario;

@Controller
public class UsuarioController {

	@RequestMapping("/loginForm")
	public String formulario() {
		return "usuario/login";
	}
	
	@RequestMapping("/efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		UsuarioDAO dao = new UsuarioDAO();
		if (dao.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
		return "redirect:loginForm";
	}
	
}
