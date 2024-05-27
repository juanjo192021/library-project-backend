package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Estudiante;
import com.library.project.web.services.dto.EstudianteDTO;
import com.library.project.web.services.dto.EstudianteSaveDTO;
import com.library.project.web.services.dto.EstudianteUpdateDTO;

public interface IEstudianteService {

	public List<Estudiante> getListEstudiantes();

	public EstudianteDTO buscarPorId(Long id);

	public EstudianteDTO guardar(EstudianteSaveDTO estudianteSaveDTO);

	public void eliminar(Long id);

	public EstudianteDTO update(EstudianteUpdateDTO estudianteUpdateDTO);

}
