package com.library.project.web.services.dto;

import java.util.List;

import lombok.Data;

@Data
public class PrestamoSaveDTO {

	private Long usuario;
	private Long estudiante;
	private List<Long> libros;
}
