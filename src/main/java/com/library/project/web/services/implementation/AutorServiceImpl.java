package com.library.project.web.services.implementation;

import java.util.*;

import com.library.project.web.exception.ArrayListFormatException;
import com.library.project.web.exception.BadRequestException;
import com.library.project.web.exception.DuplicateException;
import com.library.project.web.exception.ValidationException;
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
	public Optional<AutorDTO> buscarPorId(Long id) {
		return autorRepository.findById(id)
				.map(autor -> mapper.map(autor, AutorDTO.class));
	}

	@Override
	public AutorDTO guardar(AutorSaveDTO autorSaveDTO){
		List<String> format = new ArrayList<>();
		if(!ValidationException.esString(autorSaveDTO.getNombre())){
			format.add("nombre");
		}
		if(!ValidationException.esString(autorSaveDTO.getApellidoPaterno())){
			format.add("apellido Paterno");
		}
		if(!ValidationException.esString(autorSaveDTO.getApellidoMaterno())){
			format.add("apellido Materno");
		}
		if(!format.isEmpty()){
			throw new ArrayListFormatException(format,"author");
		}
		if (autorRepository.existsByNombre(autorSaveDTO.getNombre()) &
				autorRepository.existsByApellidoPaterno(autorSaveDTO.getApellidoPaterno()) &
				autorRepository.existsByApellidoMaterno(autorSaveDTO.getApellidoMaterno())) {
			throw new DuplicateException("author","name",autorSaveDTO.getNombre() + " " +
					autorSaveDTO.getApellidoPaterno() + " " +
					autorSaveDTO.getApellidoMaterno());
		}
		try{
			Autor autorModel = new Autor();
			Genero generoModel = generoRepository.findById(autorSaveDTO.getGenero()).orElse(null);
			autorModel.setNombre(autorSaveDTO.getNombre());
			autorModel.setApellidoPaterno(autorSaveDTO.getApellidoPaterno());
			autorModel.setApellidoMaterno(autorSaveDTO.getApellidoMaterno());
			autorModel.setGenero(generoModel);

			Autor autorSave = autorRepository.save(autorModel);

			// mapper (convierte) el objeto autorSave a un objeto de tipo AutorDTO
			AutorDTO autorDTO = mapper.map(autorSave, AutorDTO.class);
			autorDTO.setGenero(autorSave.getGenero());

			return autorDTO;
		}
		catch(Exception e){
			throw new BadRequestException("save");
		}
	}

	@Override
	public AutorDTO delete(Long id){
		Autor autor	= autorRepository.findById(id).orElse(null);
		if (autor == null){
			throw new EntityNotFoundException("AUTOR NOT FOUND IN THE DATABASE");
		}
		AutorDTO autorDTO = mapper.map(autor, AutorDTO.class);
		autorDTO.setGenero(autor.getGenero());
		autorRepository.deleteById(id);
		return autorDTO;
	}

	@Override
	public AutorDTO update(AutorUpdateDTO autorUpdateDTO){
		Autor autor	 = autorRepository.findById(autorUpdateDTO.getId()).orElse(null);
		if (autorUpdateDTO.getNombre() != null){
			autor.setNombre(autorUpdateDTO.getNombre());
		}
		if (autorUpdateDTO.getApellidoPaterno() != null){
			autor.setApellidoPaterno(autorUpdateDTO.getApellidoPaterno());
		}
		if (autorUpdateDTO.getApellidoMaterno() != null){
			autor.setApellidoMaterno(autorUpdateDTO.getApellidoMaterno());
		}
		autor = this.autorRepository.save(autor);
		AutorDTO autorDTO = mapper.map(autor, AutorDTO.class);
		return  autorDTO;
	}
}
