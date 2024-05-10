package com.library.project.web.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Estudiante;
import com.library.project.web.repository.IEstudianteRepository;
import com.library.project.web.services.IEstudianteService;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

	@Autowired
	private IEstudianteRepository estudianteRepository;
	
	@Override
	public List<Estudiante> getListEstudiantes(){		
		return estudianteRepository.findAll();
	}
}
