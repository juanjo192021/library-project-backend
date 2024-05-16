package com.library.project.web.models;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "apellido_paterno", nullable = false)
	private String apellido_paterno;
	
	@Column(name = "apellido_materno", nullable = false)
	private String apellido_materno;
	
	@Column(name = "numero_documento", nullable = false)
	private String numero_documento;
	
	@Column(name = "correo", nullable = false)
	private String correo;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "rol_id", referencedColumnName = "id")
	private Rol rol;

}
