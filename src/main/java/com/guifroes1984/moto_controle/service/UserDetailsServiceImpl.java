package com.guifroes1984.moto_controle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.guifroes1984.moto_controle.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		return (UserDetails) usuarioRepository.findByUsername(usuario)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + usuario));
	}

}
