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
import lombok.Builder;

@Entity
@Builder
@Table(name = "prestamo")
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "estado", nullable = false)
	private Integer estado;
	
	@Column(name = "fecha_prestamo", nullable = false)
	private Date fechaPrestamo;
	
	@Column(name = "fecha_devolucion", nullable = false)
	private Date fechaDevolucion;
	
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public Prestamo(){}

	public Prestamo(Integer id, Integer estado, Date fechaPrestamo, Date fechaDevolucion, Integer cantidad,
			Usuario usuario, Estudiante estudiante, List<Libro> libros) {
		this.id = id;
		this.estado = estado;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.cantidad = cantidad;
		this.usuario = usuario;
		this.estudiante = estudiante;
		this.libros = libros;
	}
}
