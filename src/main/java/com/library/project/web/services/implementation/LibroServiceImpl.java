package com.library.project.web.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Libro;
import com.library.project.web.repository.ILibroRepository;
import com.library.project.web.services.ILibroService;

@Service
public class LibroServiceImpl implements ILibroService{
	
	@Autowired
	private ILibroRepository libroRepository;
	
	@Override
	public List<Libro> getListLibros(){
		return libroRepository.findAll();
	}

}
