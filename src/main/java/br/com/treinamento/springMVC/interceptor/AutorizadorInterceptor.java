package br.com.treinamento.springMVC.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{
	
	// Antes de lidar com a requisição
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, 
							 Object controller) throws Exception {
		
		// Existe requições que não necessitam que o usuário esteja logado, por exemplo, o usuário não
		//	precisa estar logado para fazer o login, ou estar logado para obter recursos do sistema, como
		//	imagens, arquivos JS, etc.
		String uri = request.getRequestURI();
		if (uri.endsWith("loginForm") || uri.endsWith("efetuarLogin") || uri.contains("resources")) {
			return true;
		}
		
		// Para pegar a session - request.getSession()
		// Para pegar um atributo da sessão - request.getSession().getAttribute("nome do atributo")
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		} else {
			// Devolve para loginForm
			response.sendRedirect("loginForm");
			// Retornando false indica que o Spring deve parar a execução e nem envia a requisição
			//	para a action.
			return false;
		}
	}

}
