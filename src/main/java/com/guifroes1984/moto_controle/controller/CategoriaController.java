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

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/ganhos")
    public ResponseEntity<List<CategoriaDTO>> listarCategoriasGanho() {
        return ResponseEntity.ok(categoriaService.listarCategoriasPorTipo(TipoCategoria.GANHO));
    }

    @GetMapping("/gastos")
    public ResponseEntity<List<CategoriaDTO>> listarCategoriasGasto() {
        return ResponseEntity.ok(categoriaService.listarCategoriasPorTipo(TipoCategoria.GASTO));
    }

    @GetMapping("/pagamentos")
    public ResponseEntity<List<CategoriaDTO>> listarMetodosPagamento() {
        return ResponseEntity.ok(categoriaService.listarCategoriasPorTipo(TipoCategoria.PAGAMENTO));
    }
}
