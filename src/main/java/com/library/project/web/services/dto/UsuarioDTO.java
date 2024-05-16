package com.library.project.web.services.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private String numero_documento;
	private String correo;
	private String rol;
	

	
}
