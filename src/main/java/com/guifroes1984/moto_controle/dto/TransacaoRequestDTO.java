package com.guifroes1984.moto_controle.dto;

import com.guifroes1984.moto_controle.enums.TipoTransacao;

public class TransacaoRequestDTO {

	private TipoTransacao tipo;
	private String categoria;
	private Double valor;
	private String data;
	private String descricao;

	public TransacaoRequestDTO() {
	}

	public TransacaoRequestDTO(TipoTransacao tipo, String categoria, Double valor, String data, String descricao) {
		super();
		this.tipo = tipo;
		this.categoria = categoria;
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
	}

	public TipoTransacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
