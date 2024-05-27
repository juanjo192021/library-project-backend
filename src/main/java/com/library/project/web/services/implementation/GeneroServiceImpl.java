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
	public Genero buscarPorId(Long id) {
		return generoRepository.findById(id).orElse(null);
	}
	
	@Override
	public Genero guardar(String genero) {
		Genero generoModel = Genero.builder().nombre(genero).build();
		return generoRepository.save(generoModel);
	}
	
	//Parametro genero: id = id buscado en la bd, nombre = nombre que se va a actualizar
	@Override
	public Genero update(Genero genero) {
		Genero generoModel = generoRepository.findById(genero.getId()).orElse(null);
		generoModel.setNombre(genero.getNombre());
		return generoRepository.save(generoModel);
	}
	
	@Override
	public void deleteGenero(Long id) {
        generoRepository.deleteById(id);
    }
}
