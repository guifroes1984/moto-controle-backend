package com.guifroes1984.moto_controle.dto;

import com.guifroes1984.moto_controle.enums.TipoTransacao;

public class TransacaoResponseDTO {

	private Long id;
	private TipoTransacao tipo;
	private String categoria;
	private Double valor;
	private String data;
	private String descricao;
	private Long usuarioId;
	private String usuarioNome;

	public TransacaoResponseDTO() {
	}

	public TransacaoResponseDTO(Long id, TipoTransacao tipo, String categoria, Double valor, String data,
			String descricao, Long usuarioId, String usuarioNome) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.categoria = categoria;
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
		this.usuarioId = usuarioId;
		this.usuarioNome = usuarioNome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

}
