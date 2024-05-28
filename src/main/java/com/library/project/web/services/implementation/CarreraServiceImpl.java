package com.library.project.web.services.implementation;

import java.util.List;

import com.library.project.web.exception.BadRequestException;
import com.library.project.web.exception.DuplicateException;
import com.library.project.web.exception.ResourceNotFoundException;
import com.library.project.web.models.Estudiante;
import com.library.project.web.repository.IEstudianteRepository;
import com.library.project.web.services.IEstudianteService;
import com.library.project.web.services.dto.AutorSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Carrera;
import com.library.project.web.repository.ICarreraRepository;
import com.library.project.web.services.ICarreraService;

@Service
public class CarreraServiceImpl implements ICarreraService {

	@Autowired
	private ICarreraRepository carreraRepository;

	@Autowired
	private IEstudianteRepository estudianteRepository;
	
	@Override
	public List<Carrera> getAll(){
		return carreraRepository.findAll();
	}
	
	@Override
	public Carrera findById(Long id) {
        return carreraRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("carrera", "id", id));
    }
	
	@Override
	public Carrera save(String carrera) {
		checkDuplicate(carrera);
		Carrera carreraModel = Carrera.builder().nombre(carrera).build();
		return carreraRepository.save(carreraModel);
	}

	private void checkDuplicate(String carrera) {
		if (carreraRepository.existsByNombre(carrera)) {
			throw new DuplicateException("carrera", "name", carrera);
		}
	}

	@Override
	public Carrera update(Carrera carrera) {
		Carrera carreraModel = carreraRepository.findById(carrera.getId()).
				orElseThrow(() -> new ResourceNotFoundException("carrera", "id", carrera.getId()));
		carreraModel.setNombre(carrera.getNombre());
		return carreraRepository.save(carreraModel);
	}
	
	@Override
	public Carrera delete(Long id) {
        Carrera carrera = carreraRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("carrera", "id", id));
		List<Estudiante> estudiantes = carrera.getEstudiantes();
		if(!estudiantes.isEmpty()){
			throw new BadRequestException("delete");
		}
		carreraRepository.deleteById(id);
		return carrera;
    }
}
