package com.library.project.web.services.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LibroSaveDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private Date fechaPublic;
	private int stock;
	private int pasillo;
	private int autor;
}
