package com.library.project.web.models;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "fecha_public", nullable = false)
	private Date fechaPublic;
	
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	
	@JoinColumn(name = "pasillo_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Pasillo pasillo;
	
	@JoinColumn(name = "genero_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Genero genero;
	
	@ManyToMany(mappedBy = "libros")
	@JsonIgnore
	private List<Prestamo> prestamos;

	public Libro() {
	}

	public Libro(Long id, String titulo, Date fechaPublic, int cantidad, Pasillo pasillo, Genero genero,
			List<Prestamo> prestamos) {
		this.id = id;
		this.titulo = titulo;
		this.fechaPublic = fechaPublic;
		this.cantidad = cantidad;
		this.pasillo = pasillo;
		this.genero = genero;
		this.prestamos = prestamos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaPublic() {
		return fechaPublic;
	}

	public void setFechaPublic(Date fechaPublic) {
		this.fechaPublic = fechaPublic;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pasillo getPasillo() {
		return pasillo;
	}

	public void setPasillo(Pasillo pasillo) {
		this.pasillo = pasillo;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
}
