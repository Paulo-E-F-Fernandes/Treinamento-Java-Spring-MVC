package br.com.treinamento.springMVC.dao.usuario;

import br.com.treinamento.springMVC.dao.base.BaseDAO;
import br.com.treinamento.springMVC.model.Usuario;

public interface UsuarioBaseDAO extends BaseDAO<Usuario> {

	public boolean existeUsuario(Usuario usuario);
	
}
