package com.library.project.web.services.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LibroUpdateDTO {

	private Long id;
	private String titulo;
	private Date fechaPublic;
	private int stock;
	private Long pasillo;
	private Long autor;
}
