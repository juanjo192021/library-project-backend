package com.library.project.web.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.exception.ResourceNotFoundException;
import com.library.project.web.models.Carrera;
import com.library.project.web.models.Estudiante;
import com.library.project.web.repository.ICarreraRepository;
import com.library.project.web.repository.IEstudianteRepository;
import com.library.project.web.services.IEstudianteService;
import com.library.project.web.services.dto.EstudianteDTO;
import com.library.project.web.services.dto.EstudianteSaveDTO;
import com.library.project.web.services.dto.EstudianteUpdateDTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private IEstudianteRepository estudianteRepository;
	
	@Autowired
	private ICarreraRepository carreraRepository;
	
	@Override
	public List<Estudiante> getListEstudiantes(){		
		return estudianteRepository.findAll();
	}
	
	@Override
	public EstudianteDTO buscarPorId(Long id) {
		Estudiante estudiante =  estudianteRepository.findById(id).orElse(null);
		EstudianteDTO estudianteDTO = mapper.map(estudiante, EstudianteDTO.class);
		return estudianteDTO;
	}
	
	@Override
	public EstudianteDTO guardar(EstudianteSaveDTO estudianteSaveDTO) {
		
		Estudiante estudianteModel = mapper.map(estudianteSaveDTO, Estudiante.class);
		Carrera carreraModel = carreraRepository.findById(estudianteSaveDTO.getCarrera()).orElse(null);
				
		estudianteModel.setCarrera(carreraModel);
		
		Estudiante estudianteSave = estudianteRepository.save(estudianteModel);

		EstudianteDTO estudianteDTO = mapper.map(estudianteSave, EstudianteDTO.class);
		estudianteDTO.setCarrera(estudianteSave.getCarrera().getNombre());
		
		return estudianteDTO;
	}
	
	@Override
	public EstudianteDTO update(EstudianteUpdateDTO estudianteUpdateDTO){
		Estudiante estudiante = estudianteRepository.findById(estudianteUpdateDTO.getId()).
				orElseThrow(() -> new ResourceNotFoundException("estudiante", "id", estudianteUpdateDTO.getId()));
				
		if(estudianteUpdateDTO.getNombre() != null) {
			estudiante.setNombre(estudianteUpdateDTO.getNombre()); 
		}
		
		if(estudianteUpdateDTO.getApellidoPaterno() != null) {
			estudiante.setApellidoPaterno(estudianteUpdateDTO.getApellidoPaterno()); 
		}
		
		if(estudianteUpdateDTO.getApellidoMaterno() != null) {
			estudiante.setApellidoMaterno(estudianteUpdateDTO.getApellidoMaterno()); 
		}
		
		if(estudianteUpdateDTO.getCodigoEstudiante() != null) {
			estudiante.setCodigoEstudiante(estudianteUpdateDTO.getCodigoEstudiante()); 
		}
		
		if(estudianteUpdateDTO.getCorreo() != null) {
			estudiante.setCorreo(estudianteUpdateDTO.getCorreo()); 
		}
		
		if(estudianteUpdateDTO.getCarrera() != null) {
			Carrera carreraModel = carreraRepository.findById(estudianteUpdateDTO.getCarrera()).orElse(null);
			estudiante.setCarrera(carreraModel); 
		}

		Estudiante estudianteUdpate = estudianteRepository.save(estudiante);
		EstudianteDTO estudianteDTO = mapper.map(estudianteUdpate, EstudianteDTO.class);
		estudianteDTO.setCarrera(estudianteUdpate.getCarrera().getNombre());
		return estudianteDTO;
	}

	@Override
	public void eliminar(Long id) {
		estudianteRepository.deleteById(id);	
	}
	
}
