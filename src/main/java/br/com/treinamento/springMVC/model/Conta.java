package br.com.treinamento.springMVC.model;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Conta extends BaseModel {

	@NotNull 
	@Size(min=5, message="{conta.formulario.descricao.tamanho.minimo}")
	private String descricao;
	
	private Boolean paga;
	private Calendar dataPagamento;
	private TipoConta tipo;
	private Double valor;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean isPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}

}
