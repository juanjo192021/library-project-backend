package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Estudiante;
import com.library.project.web.services.dto.EstudianteDTO;
import com.library.project.web.services.dto.EstudianteSaveDTO;
import com.library.project.web.services.dto.EstudianteUpdateDTO;

public interface IEstudianteService {

	public List<Estudiante> getAll();

	public EstudianteDTO findById(Long id);

	public EstudianteDTO save(EstudianteSaveDTO estudianteSaveDTO);

	public EstudianteDTO delete(Long id);

	public EstudianteDTO update(EstudianteUpdateDTO estudianteUpdateDTO);

}
