package com.library.project.web.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Builder
@Table(name="estudiante")
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "apellido_paterno", nullable = false)
	private String apellidoPaterno;
	
	@Column(name = "apellido_materno", nullable = false)
	private String apellidoMaterno;
	
	@Column(name = "numero_documento", nullable = false)
	private String numeroDocumento;
	
	@Column(name = "correo", nullable = false)
	private String correo;
	
	@Column(name = "cod_estudiante", nullable = false)
	private String codigoEstudiante;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "carrera_id", referencedColumnName = "id")
	private Carrera carrera;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCodigoEstudiante() {
		return codigoEstudiante;
	}

	public void setCodigoEstudiante(String codigoEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Estudiante() {
	}

	public Estudiante(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String numeroDocumento,
			String correo, String codigoEstudiante, Carrera carrera) {
		this.id = id;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.numeroDocumento = numeroDocumento;
		this.correo = correo;
		this.codigoEstudiante = codigoEstudiante;
		this.carrera = carrera;
	}
}
