package com.library.project.web.services.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private String password;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private String numero_documento;
	private String correo;
	private String rol;
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Integer id, String username, String password, String nombre, String apellido_paterno,
			String apellido_materno, String numero_documento, String correo, String rol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.numero_documento = numero_documento;
		this.correo = correo;
		this.rol = rol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
