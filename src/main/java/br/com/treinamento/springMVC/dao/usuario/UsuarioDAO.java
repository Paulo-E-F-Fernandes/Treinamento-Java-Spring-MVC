package br.com.treinamento.springMVC.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.treinamento.springMVC.model.Usuario;

//@Repository
public class UsuarioDAO implements UsuarioBaseDAO {

	private Connection connection;

	@Autowired
	public UsuarioDAO(DataSource ds) {
		try {
			this.connection = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
	@Override
	public void adiciona(Usuario model) {
		if(model == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO usuarios (login,senha) VALUES (?,?)");
			stmt.setString(1, model.getLogin());
			stmt.setString(2, model.getSenha());
			stmt.execute();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
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
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM usuarios WHERE login = ? and senha = ?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			rs.close();
			stmt.close();

			connection.close();
			
			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
