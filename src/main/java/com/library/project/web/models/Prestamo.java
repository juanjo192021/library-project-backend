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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prestamo")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	@JoinTable(
			name = "detalle_prestamo",
			joinColumns = @JoinColumn(name = "prestamo_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name="libro_id", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Libro> libros;
	
}
