package com.guifroes1984.moto_controle.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guifroes1984.moto_controle.enums.TipoTransacao;
import com.guifroes1984.moto_controle.model.Transacao;
import com.guifroes1984.moto_controle.model.Usuario;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	List<Transacao> findByUsuarioOrderByDataDesc(Usuario usuario);

	List<Transacao> findByUsuarioAndDataBetweenOrderByDataDesc(Usuario usuario, LocalDateTime inicio,
			LocalDateTime fim);

	@Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t " + "WHERE t.usuario = :usuario AND t.tipo = :tipo")
	Double somarPorTipo(@Param("usuario") Usuario usuario, @Param("tipo") TipoTransacao tipo);

	@Query("SELECT COUNT(t) FROM Transacao t WHERE t.usuario = :usuario")
	Long contarPorUsuario(@Param("usuario") Usuario usuario);

	@Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t " + "WHERE t.usuario = :usuario AND t.tipo = 'GANHO'")
	Double somarGanhos(@Param("usuario") Usuario usuario);

	@Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t " + "WHERE t.usuario = :usuario AND t.tipo = 'GASTO'")
	Double somarGastos(@Param("usuario") Usuario usuario);

}
