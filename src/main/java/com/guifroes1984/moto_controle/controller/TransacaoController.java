package com.guifroes1984.moto_controle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guifroes1984.moto_controle.dto.ResumoDTO;
import com.guifroes1984.moto_controle.dto.TransacaoRequestDTO;
import com.guifroes1984.moto_controle.dto.TransacaoResponseDTO;
import com.guifroes1984.moto_controle.service.TransacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/transacoes")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Transações", description = "Endpoints para gerenciar ganhos e gastos")
@SecurityRequirement(name = "bearerAuth")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;

	@Operation(summary = "Listar todas transações", description = "Retorna todas as transações do usuário logado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de transações retornada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Token inválido ou não fornecido") })
	@GetMapping
	public ResponseEntity<List<TransacaoResponseDTO>> listarTodas() {
		return ResponseEntity.ok(transacaoService.listarTodas());
	}

	@Operation(summary = "Buscar transação por ID", description = "Retorna uma transação específica")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Transação encontrada"),
			@ApiResponse(responseCode = "404", description = "Transação não encontrada") })
	@GetMapping("/{id}")
	public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(transacaoService.buscarPorId(id));
	}

	@Operation(summary = "Obter resumo financeiro", description = "Retorna total de ganhos, gastos e lucro")
	@GetMapping("/resumo")
	public ResponseEntity<ResumoDTO> obterResumo() {
		return ResponseEntity.ok(transacaoService.obterResumo());
	}

	@Operation(summary = "Criar transação", description = "Adiciona uma nova transação (ganho ou gasto)")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos") })
	@PostMapping
	public ResponseEntity<TransacaoResponseDTO> criar(@RequestBody TransacaoRequestDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.criar(request));
	}

	@Operation(summary = "Atualizar transação", description = "Atualiza os dados de uma transação existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Transação atualizada"),
			@ApiResponse(responseCode = "404", description = "Transação não encontrada") })
	@PutMapping("/{id}")
	public ResponseEntity<TransacaoResponseDTO> atualizar(@PathVariable Long id,
			@RequestBody TransacaoRequestDTO request) {
		return ResponseEntity.ok(transacaoService.atualizar(id, request));
	}

	@Operation(summary = "Deletar transação", description = "Remove uma transação do sistema")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Transação deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Transação não encontrada") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		transacaoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
