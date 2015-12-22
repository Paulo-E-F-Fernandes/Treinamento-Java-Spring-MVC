package br.com.treinamento.springMVC.dao.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.treinamento.springMVC.dao.base.BaseSingletonDAO;
import br.com.treinamento.springMVC.model.Usuario;

@Component("usuarioSingletonDAO")
public class UsuarioSingletonDAO extends BaseSingletonDAO<Usuario> implements UsuarioBaseDAO {

	private static List<Usuario> listaUsuario;

	public UsuarioSingletonDAO() {
		if (listaUsuario == null) {
			UsuarioSingletonDAO.listaUsuario = new ArrayList<Usuario>();
			Usuario usuario = new Usuario();
			usuario.setLogin("caelum");
			usuario.setSenha("online");
			super.adiciona(usuario);
		}
	}
	
	@Override
	protected void adicionaRegistro(Usuario model) {
		listaUsuario.add(model);
	}
	
	@Override
	public void remove(Usuario model) {
		// TODO Auto-generated method stub
	}

	@Override
	public void altera(Usuario model) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Usuario> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscaPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existeUsuario(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("Usuário nao deve ser nulo");
		}
		
		for (Usuario user : listaUsuario) {
			if (user.getLogin().equals(usuario.getLogin()) && user.getSenha().equals(usuario.getSenha())) {
				return true;
			}
		}
		
		return false;
	}

}
