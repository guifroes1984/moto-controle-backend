package com.guifroes1984.moto_controle.dto;

import com.guifroes1984.moto_controle.enums.TipoTransacao;

public class TransacaoResponseDTO {

	private Long id;
	private TipoTransacao tipo;
	private Long categoriaId;
	private String categoriaNome;
	private Double valor;
	private String data;
	private String descricao;
	private Long usuarioId;
	private String usuarioNome;
	private Double litros;
	private String paymentMethod;
	private boolean temComprovante;

	public TransacaoResponseDTO() {
	}

	public TransacaoResponseDTO(Long id, TipoTransacao tipo, Long categoriaId, String categoriaNome, Double valor,
			String data, String descricao, Long usuarioId, String usuarioNome, Double litros, String paymentMethod, boolean temComprovante) {
		this.id = id;
		this.tipo = tipo;
		this.categoriaId = categoriaId;
		this.categoriaNome = categoriaNome;
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
		this.usuarioId = usuarioId;
		this.usuarioNome = usuarioNome;
		this.litros = litros;
		this.paymentMethod = paymentMethod;
		this.temComprovante = temComprovante;
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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoriaNome() {
		return categoriaNome;
	}

	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
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

	public Double getLitros() {
		return litros;
	}

	public void setLitros(Double litros) {
		this.litros = litros;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public boolean isTemComprovante() {
		return temComprovante;
	}
	
	public void setTemComprovante(boolean temComprovante) {
		this.temComprovante = temComprovante;
	}

}
