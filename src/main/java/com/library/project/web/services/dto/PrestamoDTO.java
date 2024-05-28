package com.library.project.web.services.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PrestamoDTO {
	
	private Long id;
	private String estado;
	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private int cantidad;
	private String usuario;
	private String estudiante;
	private List<String> libros;

}
