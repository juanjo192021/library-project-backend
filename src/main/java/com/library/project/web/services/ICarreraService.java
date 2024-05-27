package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Carrera;

public interface ICarreraService {

	public List<Carrera> getListCarreras();

	public Carrera guardar(String carrera);

	public Carrera getCarreraById(Long id);

	public void deleteCarrera(Long id);

	public Carrera update(Carrera carrera);

}
