package com.library.project.web.services.dto;

import lombok.Data;

@Data
public class UsuarioSaveDTO {

	private String username;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private String numero_documento;
	private String correo;
	private Long rol;
}
