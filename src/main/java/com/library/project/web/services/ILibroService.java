package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Libro;
import com.library.project.web.services.dto.LibroDTO;
import com.library.project.web.services.dto.LibroSaveDTO;
import com.library.project.web.services.dto.LibroUpdateDTO;

public interface ILibroService {

	public List<Libro> getListLibros();

	public LibroDTO guardar(LibroSaveDTO libro);

	public void eliminar(Long id);

	public LibroDTO buscarPorId(Long id);

	public LibroDTO update(LibroUpdateDTO libroUpdateDTO);
}
