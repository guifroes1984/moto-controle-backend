package com.guifroes1984.moto_controle.exception;

public class TransacaoNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransacaoNaoEncontradaException(Long id) {
		super("Transação com ID " + id + " não encontrada ou foi excluída");
	}
	
	public TransacaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}
