package com.library.project.web.services.dto;

import java.util.List;

import lombok.Data;

@Data
public class PrestamoUpdateDTO {

	private Long id;
	private int estado;
	private List<Long> libros;
}
