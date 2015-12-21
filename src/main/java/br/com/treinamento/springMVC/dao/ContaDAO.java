package br.com.treinamento.springMVC.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.treinamento.springMVC.model.BaseModel;
import br.com.treinamento.springMVC.model.Conta;
import br.com.treinamento.springMVC.model.TipoConta;

/* A anotação @Repository indica para o Spring que está classe é do tipo repositório, que sabe acesar um
 	banco de dados ou algum outro lugar onde guardamos informações. Indica para o Spring que o ContaDAO
 	é um DAO. */
@Repository
public class ContaDAO {
	private Connection connection;
	
	// public ContaDAO(Connection connection) {
	@Autowired
	public ContaDAO(DataSource ds) {
		/* 
		 * Vamos injetar o Connection no ContaDAO, por isso recebemos como parâmetro, mas um vez para 
		 * 	diminuir o acoplamento.
		 * 
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		*/
		//this.connection = connection;
		try {
			/* Como foi configurado um DataSource, obtemos a Connection através do DataSource agora. */
			this.connection = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adiciona(BaseModel model) {
		try {
			Conta conta = (Conta) model; 
			String sql = "INSERT INTO contas (descricao, paga, valor, tipo) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, conta.getDescricao());
			stmt.setBoolean(2, conta.isPaga());
			stmt.setDouble(3, conta.getValor());
			stmt.setString(4, conta.getTipo().name());
			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(BaseModel model) {
		Conta conta = (Conta) model;
		if (conta.getId() == null) {
			throw new IllegalStateException("O id da conta não pode ser nulo.");
		}
		
		try {
			String sql = "DELETE FROM contas WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, conta.getId());
			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(BaseModel model) {
		Conta conta = (Conta) model;
		if (conta.getId() == null) {
			throw new IllegalStateException("O id da conta não pode ser nulo.");
		}
		
		try {
			String sql = "UPDATE contas SET descricao = ?, paga = ?, dataPagamento = ?, tipo = ?, valor = ? WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, conta.getDescricao());
			stmt.setBoolean(2, conta.isPaga());
			stmt.setDate(3, conta.getDataPagamento() != null ? new Date(conta.getDataPagamento().getTimeInMillis()) : null);
			stmt.setString(4, conta.getTipo().name());
			stmt.setDouble(5, conta.getValor());
			stmt.setLong(6, conta.getId());
			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Conta> lista() {
		try {
			List<Conta> contas = new ArrayList<Conta>();
			String sql = "SELECT * FROM contas";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				contas.add(this.populaConta(rs));
			}
			
			rs.close();
			stmt.close();
			connection.close();
			return contas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Conta buscaPorId(Long id) {
		if (id == null) {
			throw new IllegalStateException("O id da conta não pode ser nulo.");
		}
		
		try {
			String sql = "SELECT * FROM contas WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			Conta conta = null;
			
			if (rs.next()) {
				conta = populaConta(rs);
			}
			
			rs.close();
			stmt.close();
			connection.close();
			return conta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void paga(Long id) {
		if (id == null) {
			throw new IllegalStateException("O id da conta não pode ser nulo.");
		}

		try {
			String sql = "UPDATE contas SET paga = ?, dataPagamento = ? WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Conta populaConta(ResultSet rs) throws SQLException {
		Conta conta = new Conta();
		conta.setId(rs.getLong("id"));
		conta.setDescricao(rs.getString("descricao"));
		conta.setPaga(rs.getBoolean("paga"));
		conta.setValor(rs.getDouble("valor"));
		conta.setTipo(Enum.valueOf(TipoConta.class, rs.getString("tipo")));
		
		Date data = rs.getDate("dataPagamento");
		if (data != null) {
			Calendar dataPagamento = Calendar.getInstance();
			dataPagamento.setTime(data);
			conta.setDataPagamento(dataPagamento);
		}
		
		return conta;
	}

}
