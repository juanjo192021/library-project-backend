package com.library.project.web.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Genero;
import com.library.project.web.repository.IGeneroRepository;
import com.library.project.web.services.IGeneroService;

@Service
public class GeneroServiceImpl implements IGeneroService{

	@Autowired
	private IGeneroRepository generoRepository;
	
	@Override
	public List<Genero> getListGeneros(){
		return generoRepository.findAll();
	}
	
	@Override
	public Genero buscarPorId(int id) {
		return generoRepository.findById(id).orElse(null);
	}
}
