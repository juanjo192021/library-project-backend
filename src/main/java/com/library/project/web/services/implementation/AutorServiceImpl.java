package com.library.project.web.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Autor;
import com.library.project.web.repository.IAutorRepository;
import com.library.project.web.services.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService{

	@Autowired
	private IAutorRepository autorRepository;
	
	@Override
	public List<Autor> getListAutores(){
		return autorRepository.findAll();
	}
}
