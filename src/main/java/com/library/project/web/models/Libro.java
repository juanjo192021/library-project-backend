package com.library.project.web.models;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIBROS")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LIBRO_ID", nullable = false)
	private Long id;
	
	@Column(name = "CODIGO", nullable = false)
	private String codigo;
	
	@Column(name = "TITULO", nullable = false)
	private String titulo;
	
	@Column(name = "AUTOR", nullable = false)
	private String autor;
	
	@Column(name = "FECHA_PUBLICACION", nullable = false)
	private Date fechaPublicacion;
	
	@JoinColumn(name = "SECCION_ID", referencedColumnName = "SECCION_ID")
	@ManyToOne(optional = false)
	private Seccion seccion;
	
	@ManyToMany(mappedBy = "libros")
	private List<Prestamo> prestamos;
}
