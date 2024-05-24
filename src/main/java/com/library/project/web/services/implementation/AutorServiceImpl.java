package com.library.project.web.services.implementation;

import java.util.*;

import com.library.project.web.exception.*;
import com.library.project.web.models.Genero;
import com.library.project.web.repository.IGeneroRepository;
import com.library.project.web.services.dto.AutorDTO;
import com.library.project.web.services.dto.AutorSaveDTO;
import com.library.project.web.services.dto.AutorUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Autor;
import com.library.project.web.repository.IAutorRepository;
import com.library.project.web.services.IAutorService;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class AutorServiceImpl implements IAutorService{

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private IAutorRepository autorRepository;

	@Autowired
	private IGeneroRepository generoRepository;
	
	@Override
	public List<Autor> getListAutor(){
		return autorRepository.findAll();
	}

	@Override
	public AutorDTO buscarPorId(Long id) {
		Autor autor = autorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", id));
		AutorDTO autorDTO = mapper.map(autor, AutorDTO.class);
		return autorDTO;
	}

	@Override
	public AutorDTO guardar(AutorSaveDTO autorSaveDTO) {
		validarAutorSaveDTO(autorSaveDTO);
		verificarDuplicadoAutorSaveDTO(autorSaveDTO);

		Autor autorModel = new Autor();
		Genero generoModel = generoRepository.findById(autorSaveDTO.getGenero()).orElse(null);
		autorModel.setNombre(autorSaveDTO.getNombre());
		autorModel.setApellidoPaterno(autorSaveDTO.getApellidoPaterno());
		autorModel.setApellidoMaterno(autorSaveDTO.getApellidoMaterno());
		autorModel.setGenero(generoModel);

		Autor autorSave = autorRepository.save(autorModel);
		AutorDTO autorDTO = mapper.map(autorSave, AutorDTO.class);
		autorDTO.setGenero(autorSave.getGenero());
		return autorDTO;
	}

	private void validarAutorSaveDTO(AutorSaveDTO autorSaveDTO) {
		List<String> format = new ArrayList<>();

		if (!ValidationException.esString(autorSaveDTO.getNombre())) {
			format.add("nombre");
		}
		if (!ValidationException.esString(autorSaveDTO.getApellidoPaterno())) {
			format.add("apellido Paterno");
		}
		if (!ValidationException.esString(autorSaveDTO.getApellidoMaterno())) {
			format.add("apellido Materno");
		}

		if (!format.isEmpty()) {
			throw new ArrayListFormatException(format, "author");
		}
	}

	private void verificarDuplicadoAutorSaveDTO(AutorSaveDTO autorSaveDTO) {
		if (autorRepository.existsByNombre(autorSaveDTO.getNombre()) &&
				autorRepository.existsByApellidoPaterno(autorSaveDTO.getApellidoPaterno()) &&
				autorRepository.existsByApellidoMaterno(autorSaveDTO.getApellidoMaterno())) {

			throw new DuplicateException("author", "name", autorSaveDTO.getNombre() + " " +
					autorSaveDTO.getApellidoPaterno() + " " +
					autorSaveDTO.getApellidoMaterno());
		}
	}

	@Override
	public AutorDTO delete(Long id){
		Autor autor = autorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", id));
		AutorDTO autorDTO = mapper.map(autor, AutorDTO.class);
		autorDTO.setGenero(autor.getGenero());
		autorRepository.deleteById(id);
		return autorDTO;
	}

	@Override
	public AutorDTO update(AutorUpdateDTO autorUpdateDTO){
		Long id = autorUpdateDTO.getId();
		Autor autor = autorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", id));
		validarAutorUpdateDTO(autorUpdateDTO);
		verificarDuplicadoAutorUpdateDTO(autorUpdateDTO);
		autor.setNombre(autorUpdateDTO.getNombre());
		autor.setApellidoPaterno(autorUpdateDTO.getApellidoPaterno());
		autor.setApellidoMaterno(autorUpdateDTO.getApellidoMaterno());
		autor = this.autorRepository.save(autor);
		return  mapper.map(autor, AutorDTO.class);
	}

	private void validarAutorUpdateDTO(AutorUpdateDTO autorUpdateDTO) {
		List<String> format = new ArrayList<>();

		if (!ValidationException.esString(autorUpdateDTO.getNombre())) {
			format.add("nombre");
		}
		if (!ValidationException.esString(autorUpdateDTO.getApellidoPaterno())) {
			format.add("apellido Paterno");
		}
		if (!ValidationException.esString(autorUpdateDTO.getApellidoMaterno())) {
			format.add("apellido Materno");
		}

		if (!format.isEmpty()) {
			throw new ArrayListFormatException(format, "author");
		}
	}

	private void verificarDuplicadoAutorUpdateDTO(AutorUpdateDTO autorUpdateDTO) {
		if (autorRepository.existsByNombre(autorUpdateDTO.getNombre()) &&
				autorRepository.existsByApellidoPaterno(autorUpdateDTO.getApellidoPaterno()) &&
				autorRepository.existsByApellidoMaterno(autorUpdateDTO.getApellidoMaterno())) {

			throw new DuplicateException("author", "name", autorUpdateDTO.getNombre() + " " +
					autorUpdateDTO.getApellidoPaterno() + " " +
					autorUpdateDTO.getApellidoMaterno());
		}
	}
}
