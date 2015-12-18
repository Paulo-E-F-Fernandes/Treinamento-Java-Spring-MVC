package br.com.treinamento.springMVC.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.treinamento.springMVC.ConnectionFactory;

// Controller responsável por criar as tabelas no banco de dados.
@Controller
public class InfraController {

	@RequestMapping("/tabelas")
	public String criaBanco() throws SQLException { 
		Connection c = new ConnectionFactory().getConnection();
		String sql = "DROP TABLE contas IF EXISTS";
		PreparedStatement st1 = c.prepareStatement(sql);
		st1.execute();
		
		sql = "CREATE TABLE contas (id int identity, descricao varchar(255), valor double, paga boolean, dataPagamento datetime, tipo varchar(20))";
		PreparedStatement st11 = c.prepareStatement(sql);
		st11.execute();
		
		sql = "DROP TABLE usuarios IF EXISTS";
		PreparedStatement st2 = c.prepareStatement(sql);
		st2.execute();

		sql = "CREATE TABLE usuarios (login VARCHAR(255), senha VARCHAR(255));";
		PreparedStatement st22 = c.prepareStatement(sql);
		st22.execute();

		sql = "INSERT INTO usuarios (login, senha) VALUES ('caelum', 'online');";
		PreparedStatement st3 = c.prepareStatement(sql);
		st3.execute();
		
		c.close();
		
		return "infra-ok";
	}
	
}
