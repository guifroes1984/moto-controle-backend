package com.guifroes1984.moto_controle.dto;

public class CategoriaDTO {
	private Long id;
	private String nome;
	private String tipo;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String nome, String tipo) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
