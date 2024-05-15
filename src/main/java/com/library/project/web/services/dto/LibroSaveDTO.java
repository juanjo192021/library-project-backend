package com.library.project.web.services.dto;

import java.io.Serializable;
import java.util.Date;

public class LibroSaveDTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private String titulo;
	private Date fechaPublic;
	private int stock;
	private int pasillo;
	private int genero;
	
	public LibroSaveDTO() {
	}

	public LibroSaveDTO(String titulo, Date fechaPublic, int stock, int pasillo, int genero) {
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPasillo() {
		return pasillo;
	}

	public void setPasillo(int pasillo) {
		this.pasillo = pasillo;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
