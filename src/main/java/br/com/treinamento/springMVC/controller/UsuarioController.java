package br.com.treinamento.springMVC.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.treinamento.springMVC.dao.usuario.UsuarioBaseDAO;
import br.com.treinamento.springMVC.dao.usuario.UsuarioSingletonDAO;
import br.com.treinamento.springMVC.model.Usuario;

@Controller
public class UsuarioController {
	
	private UsuarioBaseDAO dao;

//	@Autowired
//	public UsuarioController(UsuarioDAO dao) {
//		this.dao = dao;
//	}
	
	@Autowired
	public UsuarioController(UsuarioSingletonDAO dao) {
		/* Não tenho banco - Descomentar no spring-context.xml */
		this.dao = dao;
	}
	

	@RequestMapping("/loginForm")
	public String formulario() {
		return "usuario/login";
	}
	
	@RequestMapping("/efetuarLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		if (dao.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
		return "redirect:loginForm";
	}
	
}
