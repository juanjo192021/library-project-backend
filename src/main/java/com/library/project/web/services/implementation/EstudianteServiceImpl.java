package com.library.project.web.services.implementation;

import java.util.List;

import com.library.project.web.exception.DuplicateException;
import com.library.project.web.services.dto.AutorSaveDTO;
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
	public List<Estudiante> getAll(){
		return estudianteRepository.findAll();
	}
	
	@Override
	public EstudianteDTO findById(Long id) {
		Estudiante estudiante =  estudianteRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("estudiante", "id", id));
		EstudianteDTO estudianteDTO = mapper.map(estudiante, EstudianteDTO.class);
		estudianteDTO.setCarrera(estudiante.getCarrera());
		return estudianteDTO;
	}
	
	@Override
	public EstudianteDTO save(EstudianteSaveDTO estudianteSaveDTO) {
		checkDuplicateName(estudianteSaveDTO);
		checkDuplicateNumberDocument(estudianteSaveDTO);

		Estudiante estudianteModel = mapper.map(estudianteSaveDTO, Estudiante.class);
		Carrera carreraModel = carreraRepository.findById(estudianteSaveDTO.getCarrera()).
				orElseThrow(() -> new ResourceNotFoundException("carrera", "id",estudianteSaveDTO.getCarrera()));
		estudianteModel.setCarrera(carreraModel);

		Estudiante estudianteSave = estudianteRepository.save(estudianteModel);

		EstudianteDTO estudianteDTO = mapper.map(estudianteSave, EstudianteDTO.class);
		estudianteDTO.setCarrera(estudianteSave.getCarrera());
		
		return estudianteDTO;
	}
	private void checkDuplicateNumberDocument(EstudianteSaveDTO estudianteSaveDTO) {
		if (estudianteRepository.existsByNumeroDocumento(estudianteSaveDTO.getNumeroDocumento())) {
			throw new DuplicateException("estudiante", "number document", estudianteSaveDTO.getNumeroDocumento());
		}
	}

	private void checkDuplicateName(EstudianteSaveDTO estudianteSaveDTO) {
		if (estudianteRepository.existsByNombre(estudianteSaveDTO.getNombre()) &&
				estudianteRepository.existsByApellidoPaterno(estudianteSaveDTO.getApellidoPaterno()) &&
				estudianteRepository.existsByApellidoMaterno(estudianteSaveDTO.getApellidoMaterno())) {

			throw new DuplicateException("estudiante", "name", estudianteSaveDTO.getNombre() + " " +
					estudianteSaveDTO.getApellidoPaterno() + " " +
					estudianteSaveDTO.getApellidoMaterno());
		}
	}
	
	@Override
	public EstudianteDTO update(EstudianteUpdateDTO estudianteUpdateDTO){
		Estudiante estudiante = estudianteRepository.findById(estudianteUpdateDTO.getId()).
				orElseThrow(() -> new ResourceNotFoundException("estudiante", "id", estudianteUpdateDTO.getId()));
		mapper.map(estudianteUpdateDTO,estudiante);
		Carrera carreraModel = carreraRepository.findById(estudianteUpdateDTO.getCarrera()).
				orElseThrow(() -> new ResourceNotFoundException("carrera", "id",estudianteUpdateDTO.getCarrera()));
		estudiante.setCarrera(carreraModel);

		Estudiante estudianteUdpate = estudianteRepository.save(estudiante);
		EstudianteDTO estudianteDTO = mapper.map(estudianteUdpate, EstudianteDTO.class);
		estudianteDTO.setCarrera(estudianteUdpate.getCarrera());
		return estudianteDTO;
	}

	@Override
	public EstudianteDTO delete(Long id) {
		Estudiante estudiante = estudianteRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("estudiante", "id", id));
		EstudianteDTO estudianteDTO = mapper.map(estudiante, EstudianteDTO.class);
		estudianteDTO.setCarrera(estudiante.getCarrera());
		estudianteRepository.deleteById(id);
		return estudianteDTO;
	}
	
}
