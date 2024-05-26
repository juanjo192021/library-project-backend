package com.library.project.web.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;

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

	@JoinTable(
			name = "detalle_autor",
			joinColumns = @JoinColumn(name = "autor_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name="genero_id",nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Genero> generos;
}
