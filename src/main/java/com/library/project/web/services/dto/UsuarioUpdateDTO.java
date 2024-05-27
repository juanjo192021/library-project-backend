package com.library.project.web.services.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDTO {

	private Long id;
	private String username;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private String correo;
	private Long rol;
}
