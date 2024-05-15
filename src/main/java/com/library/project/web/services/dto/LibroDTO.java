package com.library.project.web.services.dto;

import java.io.Serializable;
import java.util.Date;

public class LibroDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private Date fechaPublic;
	private Integer stock;
	private String pasillo;
	private String genero;
	
	
	public LibroDTO() {
	}

	public LibroDTO(String titulo, Date fechaPublic, Integer stock, String pasillo, String genero) {
		this.titulo = titulo;
		this.fechaPublic = fechaPublic;
		this.stock = stock;
		this.pasillo = pasillo;
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaPublic() {
		return fechaPublic;
	}

	public void setFechaPublic(Date fechaPublic) {
		this.fechaPublic = fechaPublic;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getPasillo() {
		return pasillo;
	}

	public void setPasillo(String pasillo) {
		this.pasillo = pasillo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
}
