package com.guifroes1984.moto_controle.dto;

public class ResumoDTO {

	private Double totalGanhos;
	private Double totalGastos;
	private Double lucroLiquido;
	private Long quantidadeTransacoes;

	public ResumoDTO() {
	}

	public ResumoDTO(Double totalGanhos, Double totalGastos, Double lucroLiquido, Long quantidadeTransacoes) {
		super();
		this.totalGanhos = totalGanhos;
		this.totalGastos = totalGastos;
		this.lucroLiquido = lucroLiquido;
		this.quantidadeTransacoes = quantidadeTransacoes;
	}

	public Double getTotalGanhos() {
		return totalGanhos;
	}

	public void setTotalGanhos(Double totalGanhos) {
		this.totalGanhos = totalGanhos != null ? totalGanhos : 0.0;
		this.lucroLiquido = this.totalGanhos - this.totalGastos;
	}

	public Double getTotalGastos() {
		return totalGastos;
	}

	public void setTotalGastos(Double totalGastos) {
		this.totalGastos = totalGanhos != null ? totalGastos : 0.0;
		this.lucroLiquido = this.totalGanhos - this.totalGanhos;
	}

	public Double getLucroLiquido() {
		return lucroLiquido;
	}

	public void setLucroLiquido(Double lucroLiquido) {
		this.lucroLiquido = lucroLiquido;
	}

	public Long getQuantidadeTransacoes() {
		return quantidadeTransacoes;
	}

	public void setQuantidadeTransacoes(Long quantidadeTransacoes) {
		this.quantidadeTransacoes = quantidadeTransacoes;
	}
	
	

}
