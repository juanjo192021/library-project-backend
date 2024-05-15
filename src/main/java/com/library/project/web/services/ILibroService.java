package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Libro;
import com.library.project.web.services.dto.LibroDTO;
import com.library.project.web.services.dto.LibroSaveDTO;

public interface ILibroService {

	public List<Libro> getListLibros();

	public LibroDTO buscarPorId(int id);

	public LibroDTO guardar(LibroSaveDTO libro);

	public void eliminar(Integer id);
}
