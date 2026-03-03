package com.guifroes1984.moto_controle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.guifroes1984.moto_controle.dto.ComprovanteResponseDTO;
import com.guifroes1984.moto_controle.model.Comprovante;
import com.guifroes1984.moto_controle.service.ComprovanteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/comprovantes")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Comprovantes", description = "Endpoints para gerenciar upload e download de comprovantes")
public class ComprovanteController {

	@Autowired
	private ComprovanteService comprovanteService;

	@Operation(summary = "Fazer upload de comprovante", description = "Envia um arquivo (imagem ou PDF) como comprovante para uma transação")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Upload realizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComprovanteResponseDTO.class))),
			@ApiResponse(responseCode = "400", description = "Arquivo inválido (vazio, tipo não permitido ou muito grande)"),
			@ApiResponse(responseCode = "404", description = "Transação não encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@PostMapping("/upload/{transacaoId}")
	public ResponseEntity<?> uploadComprovante(@PathVariable Long transacaoId,
			@RequestParam("file") MultipartFile arquivo) {

		if (arquivo.isEmpty()) {
			return ResponseEntity.badRequest().body("Arquivo vazio");
		}

		String contentType = arquivo.getContentType();
		if (contentType == null || !(contentType.startsWith("image/") || contentType.equals("application/pdf"))) {
			return ResponseEntity.badRequest().body("Apenas imagens e PDF são permitidos");
		}

		if (arquivo.getSize() > 10 * 1024 * 1024) {
			return ResponseEntity.badRequest().body("Arquivo muito grande. Máximo 10MB");
		}

		try {
			Comprovante comprovante = comprovanteService.salvarComprovante(transacaoId, arquivo);
			return ResponseEntity.ok(comprovanteService.converterParaDTO(comprovante));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Erro ao fazer upload: " + e.getMessage());
		}

	}

	@Operation(summary = "Buscar comprovante por transação", description = "Retorna os dados do comprovante associado a uma transação")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Comprovante encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ComprovanteResponseDTO.class))),
			@ApiResponse(responseCode = "404", description = "Comprovante não encontrado") })
	@GetMapping("/transacao/{transacaoId}")
	public ResponseEntity<?> buscarComprovante(@PathVariable Long transacaoId) {
		Comprovante comprovante = comprovanteService.buscarPorTransacaoId(transacaoId);
		if (comprovante == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(comprovanteService.converterParaDTO(comprovante));
	}

	@Operation(summary = "Deletar comprovante", description = "Remove o comprovante associado a uma transação")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Comprovante deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Comprovante não encontrado") })
	@DeleteMapping("/transacao/{transacaoId}")
	public ResponseEntity<?> deletarComprovate(@PathVariable Long transacaoId) {
		comprovanteService.deletarPorTransacaoId(transacaoId);
		return ResponseEntity.ok().build();
	}

}
