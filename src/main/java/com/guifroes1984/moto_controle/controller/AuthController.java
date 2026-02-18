package com.guifroes1984.moto_controle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guifroes1984.moto_controle.dto.LoginRequest;
import com.guifroes1984.moto_controle.dto.LoginResponse;
import com.guifroes1984.moto_controle.dto.RegisterRequest;
import com.guifroes1984.moto_controle.model.Usuario;
import com.guifroes1984.moto_controle.repository.UsuarioRepository;
import com.guifroes1984.moto_controle.service.JwtService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getSenha()));

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String jwtToken = jwtService.generateToken(userDetails);

			Usuario usuario = usuarioRepository.findByUsuario(request.getUsuario()).orElseThrow();

			LoginResponse response = new LoginResponse(jwtToken, "Bearer", usuario.getId(), usuario.getUsuario(),
					usuario.getEmail(), usuario.getRole());

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

		if (usuarioRepository.existsByUsuario(request.getUsuario())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de usuário já existe");
		}

		if (usuarioRepository.existsByEmail(request.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado");
		}

		try {
			Usuario usuario = new Usuario();
			usuario.setUsuario(request.getUsuario());
			usuario.setEmail(request.getEmail());
			usuario.setSenha(passwordEncoder.encode(request.getSenha()));
			usuario.setRole("USER");

			Usuario savedUser = usuarioRepository.save(usuario);

			UserDetails userDetails = org.springframework.security.core.userdetails.User
					.withUsername(savedUser.getUsuario())
					.password(savedUser.getSenha())
					.authorities("ROLE_" + savedUser.getRole()).build();

			String jwtToken = jwtService.generateToken(userDetails);

			LoginResponse response = new LoginResponse(jwtToken, "Bearer", savedUser.getId(), savedUser.getUsuario(),
					savedUser.getEmail(), savedUser.getRole());

			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao criar usuário: " + e.getMessage());
		}
	}
}
