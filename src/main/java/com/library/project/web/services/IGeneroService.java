package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Genero;

public interface IGeneroService {

	public List<Genero> getListGeneros();

	public Genero buscarPorId(Long id);

	public Genero guardar(String genero);

	public void deleteGenero(Long id);

	public Genero update(Genero genero);

}
