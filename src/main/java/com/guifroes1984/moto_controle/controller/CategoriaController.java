package com.guifroes1984.moto_controle.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guifroes1984.moto_controle.dto.CategoriaDTO;
import com.guifroes1984.moto_controle.enums.TipoCategoria;
import com.guifroes1984.moto_controle.model.Usuario;
import com.guifroes1984.moto_controle.repository.CategoriaRepository;
import com.guifroes1984.moto_controle.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
	
	@Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUsuarioAtual() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return usuarioRepository.findByUsuario(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @GetMapping("/ganhos")
    public ResponseEntity<List<CategoriaDTO>> listarCategoriasGanho() {
        Usuario usuario = getUsuarioAtual();
        return ResponseEntity.ok(
            categoriaRepository.findCategoriasUsuario(usuario, TipoCategoria.GANHO)
                .stream()
                .map(c -> new CategoriaDTO(c.getId(), c.getNome(), "GANHO"))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/gastos")
    public ResponseEntity<List<CategoriaDTO>> listarCategoriasGasto() {
        Usuario usuario = getUsuarioAtual();
        return ResponseEntity.ok(
            categoriaRepository.findCategoriasUsuario(usuario, TipoCategoria.GASTO)
                .stream()
                .map(c -> new CategoriaDTO(c.getId(), c.getNome(), "GASTO"))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/pagamentos")
    public ResponseEntity<List<CategoriaDTO>> listarMetodosPagamento() {
        Usuario usuario = getUsuarioAtual();
        return ResponseEntity.ok(
            categoriaRepository.findCategoriasUsuario(usuario, TipoCategoria.PAGAMENTO)
                .stream()
                .map(c -> new CategoriaDTO(c.getId(), c.getNome(), "PAGAMENTO"))
                .collect(Collectors.toList())
        );
    }
}
