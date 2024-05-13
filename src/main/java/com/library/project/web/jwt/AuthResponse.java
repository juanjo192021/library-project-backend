package com.library.project.web.jwt;

import com.library.project.web.models.Usuario;

public class AuthResponse {

	private Usuario usuario;
	private String accessToken;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public AuthResponse() {
		
	}

	public AuthResponse(Usuario usuario, String accessToken) {
		this.usuario = usuario;
		this.accessToken = accessToken;
	}
}
