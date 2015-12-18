package br.com.treinamento.springMVC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.treinamento.springMVC.ConnectionFactory;
import br.com.treinamento.springMVC.model.BaseModel;
import br.com.treinamento.springMVC.model.Conta;
import br.com.treinamento.springMVC.model.TipoConta;

public class ContaDAO implements BaseDAO {
	private Connection connection;
	
	@Override
	public void pegaConexao() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void adiciona(BaseModel model) {
		try {
			this.pegaConexao();
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
	
	@Override
	public void remove(BaseModel model) {
		Conta conta = (Conta) model;
		if (conta.getId() == null) {
			throw new IllegalStateException("O id da conta não pode ser nulo.");
		}
		
		try {
			this.pegaConexao();
			String sql = "DELETE FROM contas WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, conta.getId());
			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void altera(BaseModel model) {
		Conta conta = (Conta) model;
		if (conta.getId() == null) {
			throw new IllegalStateException("O id da conta não pode ser nulo.");
		}
		
		try {
			this.pegaConexao();		
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
	
	@Override
	public List<Conta> lista() {
		try {
			this.pegaConexao();
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
			this.pegaConexao();
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
