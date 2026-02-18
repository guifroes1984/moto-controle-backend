package com.guifroes1984.moto_controle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifroes1984.moto_controle.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByUsername(String usuario);
	Boolean existsByUsername(String usuario);
	Boolean existsByEmail(String email);

}
