package com.guifroes1984.moto_controle.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.guifroes1984.moto_controle.dto.ResumoDTO;
import com.guifroes1984.moto_controle.dto.TransacaoRequestDTO;
import com.guifroes1984.moto_controle.dto.TransacaoResponseDTO;
import com.guifroes1984.moto_controle.exception.TransacaoNaoEncontradaException;
import com.guifroes1984.moto_controle.model.Categoria;
import com.guifroes1984.moto_controle.model.Transacao;
import com.guifroes1984.moto_controle.model.Usuario;
import com.guifroes1984.moto_controle.repository.CategoriaRepository;
import com.guifroes1984.moto_controle.repository.TransacaoRepository;
import com.guifroes1984.moto_controle.repository.UsuarioRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Usuario getUsuarioAtual() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioRepository.findByUsuario(userDetails.getUsername())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}

	public TransacaoResponseDTO converterParaDTO(Transacao transacao) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return new TransacaoResponseDTO(transacao.getId(), transacao.getTipo(), transacao.getCategoria().getId(),
				transacao.getCategoria().getNome(), transacao.getValor(), transacao.getData().format(formatter),
				transacao.getDescricao(), transacao.getUsuario().getId(), transacao.getUsuario().getUsuario(),
				transacao.getLitros(), transacao.getPaymentMethod());
	}

	public List<TransacaoResponseDTO> listarTodas() {
		Usuario usuario = getUsuarioAtual();
		return transacaoRepository.findByUsuarioOrderByDataDesc(usuario).stream().map(this::converterParaDTO)
				.collect(Collectors.toList());
	}

	public TransacaoResponseDTO buscarPorId(Long id) {
		Usuario usuario = getUsuarioAtual();
		Transacao transacao = transacaoRepository.findById(id)
				.orElseThrow(() -> new TransacaoNaoEncontradaException(id));

		if (!transacao.getUsuario().getId().equals(usuario.getId())) {
			throw new RuntimeException("Acesso negado a esta transação");
		}
		return converterParaDTO(transacao);
	}

	public TransacaoResponseDTO criar(TransacaoRequestDTO request) {
		Usuario usuario = getUsuarioAtual();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataLocal = LocalDate.parse(request.getData(), formatter);
		LocalDateTime dataHora = dataLocal.atTime(LocalTime.NOON);

		Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

		Transacao transacao = new Transacao();
		transacao.setTipo(request.getTipo());
		transacao.setCategoria(categoria);
		transacao.setValor(request.getValor());
		transacao.setData(dataHora);
		transacao.setDescricao(request.getDescricao());
		transacao.setUsuario(usuario);

		transacao.setLitros(request.getLitros());
		transacao.setPaymentMethod(request.getPaymentMethod());

		Transacao salva = transacaoRepository.save(transacao);
		return converterParaDTO(salva);
	}

	public TransacaoResponseDTO atualizar(Long id, TransacaoRequestDTO request) {
		Usuario usuario = getUsuarioAtual();

		Transacao transacao = transacaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transação não encontrada"));

		if (!transacao.getUsuario().getId().equals(usuario.getId())) {
			throw new RuntimeException("Acesso negado a esta transação");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataLocal = LocalDate.parse(request.getData(), formatter);
		LocalDateTime dataHora = dataLocal.atTime(LocalTime.NOON);

		Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

		transacao.setTipo(request.getTipo());
		transacao.setCategoria(categoria);
		transacao.setValor(request.getValor());
		transacao.setData(dataHora);
		transacao.setDescricao(request.getDescricao());
		transacao.setLitros(request.getLitros());
		transacao.setPaymentMethod(request.getPaymentMethod());

		Transacao atualizada = transacaoRepository.save(transacao);

		Transacao transacaoRecarregada = transacaoRepository.findById(atualizada.getId())
				.orElseThrow(() -> new RuntimeException("Erro ao recarregar transação"));

		return converterParaDTO(transacaoRecarregada);
	}

	public void deletar(Long id) {
		Usuario usuario = getUsuarioAtual();
		Transacao transacao = transacaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transação não encontrada"));

		if (!transacao.getUsuario().getId().equals(usuario.getId())) {
			throw new TransacaoNaoEncontradaException(id);
		}

		transacaoRepository.delete(transacao);
	}

	public ResumoDTO obterResumo() {
		Usuario usuario = getUsuarioAtual();

		Double totalGanhos = transacaoRepository.somarGanhos(usuario);
		Double totalGastos = transacaoRepository.somarGastos(usuario);
		Long quantidade = transacaoRepository.contarPorUsuario(usuario);

		return new ResumoDTO(totalGanhos, totalGastos, quantidade);
	}

}
