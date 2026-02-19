package com.guifroes1984.moto_controle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@RequestMapping("/api/transacoes")
@CrossOrigin(origins = "http://localhost:4200")
public class TransacaoController {
	
	@Autowired
	private TransacaoService transacaoService;
	
	@GetMapping
	public ResponseEntity<List<TransacaoResponseDTO>> listarTodas() {
		return ResponseEntity.ok(transacaoService.listarTodas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(transacaoService.buscarPorId(id));
	}
	
	@GetMapping("/resumo")
	public ResponseEntity<ResumoDTO> obterResumo() {
		return ResponseEntity.ok(transacaoService.obterResumo());
	}
	
	@PostMapping
	public ResponseEntity<TransacaoResponseDTO> criar(@RequestBody TransacaoRequestDTO request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.criar(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TransacaoResponseDTO> atualizar(
			@PathVariable Long id, 
			@RequestBody TransacaoRequestDTO request) {
		return ResponseEntity.ok(transacaoService.atualizar(id, request));
	}
	
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		transacaoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
