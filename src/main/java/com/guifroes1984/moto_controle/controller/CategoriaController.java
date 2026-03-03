package com.guifroes1984.moto_controle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guifroes1984.moto_controle.dto.CategoriaDTO;
import com.guifroes1984.moto_controle.enums.TipoCategoria;
import com.guifroes1984.moto_controle.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Categorias", description = "Endpoints para listar categorias de transações")
@SecurityRequirement(name = "bearerAuth")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Operation(summary = "Listar categorias de ganho", description = "Retorna todas as categorias disponíveis para transações do tipo GANHO")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Não autorizado") })
	@GetMapping("/ganhos")
	public ResponseEntity<List<CategoriaDTO>> listarCategoriasGanho() {
		return ResponseEntity.ok(categoriaService.listarCategoriasPorTipo(TipoCategoria.GANHO));
	}

	@Operation(summary = "Listar categorias de gasto", description = "Retorna todas as categorias disponíveis para transações do tipo GASTO")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Não autorizado") })
	@GetMapping("/gastos")
	public ResponseEntity<List<CategoriaDTO>> listarCategoriasGasto() {
		return ResponseEntity.ok(categoriaService.listarCategoriasPorTipo(TipoCategoria.GASTO));
	}

	@Operation(summary = "Listar métodos de pagamento", description = "Retorna todas as formas de pagamento disponíveis para transações")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de métodos retornada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Não autorizado") })
	@GetMapping("/pagamentos")
	public ResponseEntity<List<CategoriaDTO>> listarMetodosPagamento() {
		return ResponseEntity.ok(categoriaService.listarCategoriasPorTipo(TipoCategoria.PAGAMENTO));
	}
}
