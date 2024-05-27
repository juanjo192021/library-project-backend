package com.library.project.web.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Carrera;
import com.library.project.web.repository.ICarreraRepository;
import com.library.project.web.services.ICarreraService;

@Service
public class CarreraServiceImpl implements ICarreraService {

	@Autowired
	private ICarreraRepository carreraRepository;
	
	@Override
	public List<Carrera> getListCarreras(){		
		return carreraRepository.findAll();
	}
	
	@Override
	public Carrera getCarreraById(Long id) {
        return carreraRepository.findById(id).orElse(null);
    }
	
	@Override
	public Carrera guardar(String carrera) {
		Carrera carreraModel = Carrera.builder().nombre(carrera).build();
		return carreraRepository.save(carreraModel);
	}
	
	
	//Parametro carrera: id = id buscado en la bd, nombre = nombre que se va a actualizar
	@Override
	public Carrera update(Carrera carrera) {
		Carrera carreraModel = carreraRepository.findById(carrera.getId()).orElse(null);
		carreraModel.setNombre(carrera.getNombre());
		return carreraRepository.save(carreraModel);
	}
	
	@Override
	public void deleteCarrera(Long id) {
        carreraRepository.deleteById(id);
    }
}
