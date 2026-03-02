package com.guifroes1984.moto_controle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifroes1984.moto_controle.model.Comprovante;
import com.guifroes1984.moto_controle.model.Transacao;

public interface ComprovanteRepository extends JpaRepository<Comprovante, Long> {

	Optional<Comprovante> findByTransacao(Transacao transacao);

	Optional<Comprovante> findByTransacaoId(Long transacaoId);

	void deleteByTransacaoId(Long transacaoId);

}
