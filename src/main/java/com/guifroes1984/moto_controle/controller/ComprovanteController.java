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

import com.guifroes1984.moto_controle.model.Comprovante;
import com.guifroes1984.moto_controle.service.ComprovanteService;

@RestController
@RequestMapping("/api/comprovantes")
@CrossOrigin(origins = "http://localhost:4200")
public class ComprovanteController {

	@Autowired
	private ComprovanteService comprovanteService;

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
			Comprovante comprovante = comprovanteService.salvarComprovate(transacaoId, arquivo);
			return ResponseEntity.ok(comprovante);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Erro ao fazer upload: " + e.getMessage());
		}

	}
	
	@GetMapping("/transacao/{transacaoId}")
	public ResponseEntity<?> buscarComprovante(@PathVariable Long transacaoId) {
		Comprovante comprovante = comprovanteService.buscarPorTransacaoId(transacaoId);
		if (comprovante == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(comprovante);
	}
	
	@DeleteMapping("/transacao/{transacaoId}")
	public ResponseEntity<?> deletarComprovate(@PathVariable Long transacaoId) {
		comprovanteService.deletarPorTransacaoId(transacaoId);
		return ResponseEntity.ok().build();
	}

}
