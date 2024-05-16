package com.library.project.web.models;

import java.io.Serializable;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "prestamo")
public class Prestamo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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
	
}
