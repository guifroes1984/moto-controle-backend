package com.guifroes1984.moto_controle.dto;

public class LoginResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String usuario;
	private String email;
	private String role;

	public LoginResponse() {

	}

	public LoginResponse(String token, String type, Long id, String usuario, String email, String role) {
		this.token = token;
		this.type = type;
		this.id = id;
		this.usuario = usuario;
		this.email = email;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
