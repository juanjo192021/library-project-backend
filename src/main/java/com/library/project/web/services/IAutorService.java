package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Autor;
import com.library.project.web.services.dto.AutorDTO;
import com.library.project.web.services.dto.AutorSaveDTO;
import com.library.project.web.services.dto.AutorUpdateDTO;
import com.library.project.web.services.dto.LibroDTO;

public interface IAutorService {

	public List<Autor> getListAutores();

	public AutorDTO guardar(AutorSaveDTO autor);

	public AutorDTO buscarPorId(Long id);

	public AutorDTO delete(Long id);

	public AutorDTO update(AutorUpdateDTO autor);
}
