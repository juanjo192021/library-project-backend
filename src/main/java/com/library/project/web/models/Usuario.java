package com.library.project.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USUARIO_ID", nullable = false)
	private Long id;
	
	@Column(name = "CODIGO", nullable = false)
	private String codigo;
	
	@Column(name = "NOMBRES", nullable = false)
	private String nombres;
	
	@Column(name = "APELLIDOS", nullable = false)
	private String apellidos;
	
	@Column(name = "NOMBRE_USUARIO", nullable = false)
	private String nombreUsuario;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
	@ManyToOne(optional = false)
	private Rol rol;
}
