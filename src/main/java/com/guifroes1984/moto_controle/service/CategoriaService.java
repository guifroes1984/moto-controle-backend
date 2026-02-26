package com.guifroes1984.moto_controle.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.guifroes1984.moto_controle.dto.CategoriaDTO;
import com.guifroes1984.moto_controle.enums.TipoCategoria;
import com.guifroes1984.moto_controle.model.Categoria;
import com.guifroes1984.moto_controle.model.Usuario;
import com.guifroes1984.moto_controle.repository.CategoriaRepository;
import com.guifroes1984.moto_controle.repository.UsuarioRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Usuario getUsuarioAtual() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioRepository.findByUsuario(userDetails.getUsername())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}

	private CategoriaDTO converterParaDTO(Categoria categoria) {
		return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getTipo().toString());
	}

	public List<CategoriaDTO> listarCategoriasPorTipo(TipoCategoria tipo) {
		Usuario usuario = getUsuarioAtual();

		List<Categoria> categorias = categoriaRepository.findCategoriasDisponiveis(usuario, tipo);

		return categorias.stream().map(this::converterParaDTO).collect(Collectors.toList());
	}

}
