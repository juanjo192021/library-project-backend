package com.library.project.web.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRESTAMOS")
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRESTAMO_ID", nullable = false)
	private Long id;
	
	@Column(name = "ESTADO", nullable = false)
	private int estado;
	
	@Column(name = "FECHA_PRESTAMO", nullable = false)
	private Date fechaPrestamo;
	
	@Column(name = "FECHA_DEVOLUCION", nullable = false)
	private Date fechaDevoilucion;
	
	@Column(name = "CANTIDAD", nullable = false)
	private int Cantidad;
	
	@JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	@JoinTable(
			name = "REL_PRESTAMOS_LIBROS",
			joinColumns = @JoinColumn(name = "PRESTAMOS_ID", nullable = false),
			inverseJoinColumns = @JoinColumn(name="LIBROS_ID", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Libro> libros;
}
