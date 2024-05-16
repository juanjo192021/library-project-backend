package com.library.project.web.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="autor")
public class Autor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "apellido_paterno", nullable = false)
	private String apellidoPaterno;

	@Column(name = "apellido_materno", nullable = false)
	private String apellidoMaterno;
	
	@ManyToOne
	@JoinColumn(name = "genero_id", referencedColumnName = "id")
	private Genero genero;


}
