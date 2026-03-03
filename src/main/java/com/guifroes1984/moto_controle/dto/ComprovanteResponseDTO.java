package com.guifroes1984.moto_controle.dto;

public class ComprovanteResponseDTO {

	private Long id;
	private String nomeOriginal;
	private String nomeArquivo;
	private String tipoArquivo;
	private Long tamanho;
	private String caminho;
	private String dataUpload;
	private Long transacaoId;
	private String transacaoDescricao;

	public ComprovanteResponseDTO() {
	}

	public ComprovanteResponseDTO(Long id, String nomeOriginal, String nomeArquivo, String tipoArquivo, Long tamanho,
			String caminho, String dataUpload, Long transacaoId, String transacaoDescricao) {
		this.id = id;
		this.nomeOriginal = nomeOriginal;
		this.nomeArquivo = nomeArquivo;
		this.tipoArquivo = tipoArquivo;
		this.tamanho = tamanho;
		this.caminho = caminho;
		this.dataUpload = dataUpload;
		this.transacaoId = transacaoId;
		this.transacaoDescricao = transacaoDescricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(String dataUpload) {
		this.dataUpload = dataUpload;
	}

	public Long getTransacaoId() {
		return transacaoId;
	}

	public void setTransacaoId(Long transacaoId) {
		this.transacaoId = transacaoId;
	}

	public String getTransacaoDescricao() {
		return transacaoDescricao;
	}

	public void setTransacaoDescricao(String transacaoDescricao) {
		this.transacaoDescricao = transacaoDescricao;
	}

}
