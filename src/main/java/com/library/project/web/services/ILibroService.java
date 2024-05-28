package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Libro;
import com.library.project.web.services.dto.LibroDTO;
import com.library.project.web.services.dto.LibroSaveDTO;
import com.library.project.web.services.dto.LibroUpdateDTO;

public interface ILibroService {

	public List<Libro> getAll();

	public LibroDTO save(LibroSaveDTO libro);

	public LibroDTO findById(Long id);

	public LibroDTO update(LibroUpdateDTO libroUpdateDTO);
}
