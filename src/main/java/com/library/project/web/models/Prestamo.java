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
@Table(name = "prestamo")
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "estado", nullable = false)
	private int estado;
	
	@Column(name = "fecha_prestamo", nullable = false)
	private Date fechaPrestamo;
	
	@Column(name = "fecha_devolucion", nullable = false)
	private Date fechaDevoilucion;
	
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	@JoinColumn(name = "estudiante_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Estudiante estudiante;
	
	@JoinTable(
			name = "detalle_prestamo",
			joinColumns = @JoinColumn(name = "prestamo_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name="libro_id", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Libro> libros;
	
	public Prestamo() {
	}

	public Prestamo(Long id, int estado, Date fechaPrestamo, Date fechaDevoilucion, int cantidad, Usuario usuario,
			List<Libro> libros) {
		this.id = id;
		this.estado = estado;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevoilucion = fechaDevoilucion;
		this.cantidad = cantidad;
		this.usuario = usuario;
		this.libros = libros;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaDevoilucion() {
		return fechaDevoilucion;
	}

	public void setFechaDevoilucion(Date fechaDevoilucion) {
		this.fechaDevoilucion = fechaDevoilucion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
}
