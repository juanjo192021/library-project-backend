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
	public AutorDTO save(AutorSaveDTO autorSaveDTO){
		checkDuplicate(autorSaveDTO);
		Autor autorModel = new Autor();
		List<Genero> generoModel = generoRepository.findAllById(autorSaveDTO.getGenero());
		autorModel.setNombre(autorSaveDTO.getNombre());
		autorModel.setApellidoPaterno(autorSaveDTO.getApellidoPaterno());
		autorModel.setApellidoMaterno(autorSaveDTO.getApellidoMaterno());
		autorModel.setGeneros(generoModel);

		Autor autorSave = autorRepository.save(autorModel);
		AutorDTO autorDTO = mapper.map(autorSave, AutorDTO.class);
		autorDTO.setGenero(generoModel);
		return autorDTO;
	}

	private void checkDuplicate(AutorSaveDTO autorSaveDTO) {
		if (autorRepository.existsByNombre(autorSaveDTO.getNombre()) &&
				autorRepository.existsByApellidoPaterno(autorSaveDTO.getApellidoPaterno()) &&
				autorRepository.existsByApellidoMaterno(autorSaveDTO.getApellidoMaterno())) {

			throw new DuplicateException("author", "name", autorSaveDTO.getNombre() + " " +
					autorSaveDTO.getApellidoPaterno() + " " +
					autorSaveDTO.getApellidoMaterno());
		}
	}

	@Override
	public List<Autor> getAll(){
		return autorRepository.findAll();
	}

	@Override
	public AutorDTO findById (Long id){
		Autor autor = autorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", id));
		List<Genero> generoModel = autor.getGeneros();
		AutorDTO autorDTO = mapper.map(autor, AutorDTO.class);
		autorDTO.setGenero(generoModel);
		return autorDTO;
	}

	@Override
	public AutorDTO update(AutorUpdateDTO autorUpdateDTO){
		Autor autor = autorRepository.findById(autorUpdateDTO.getId()).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", autorUpdateDTO.getId()));
		List<Genero> generoModel = generoRepository.findAllById(autorUpdateDTO.getGenero());
		autor.setNombre(autorUpdateDTO.getNombre());
		autor.setApellidoPaterno(autorUpdateDTO.getApellidoPaterno());
		autor.setApellidoMaterno(autorUpdateDTO.getApellidoMaterno());
		autor.setGeneros(generoModel);
		Autor autorUpdate = autorRepository.save(autor);
		AutorDTO autorDTO = mapper.map(autorUpdate,AutorDTO.class);
		autorDTO.setGenero(autorUpdate.getGeneros());
		return autorDTO;
	}

	/*
	@Override
	public AutorDTO delete(Long id){
		Autor autor = autorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", id));
		List<Genero> generoModel = autor.getGeneros();
		AutorDTO autorDTO = mapper.map(autor, AutorDTO.class);
		autorDTO.setGenero(generoModel);
		autorRepository.deleteDetalleAutorByAutorId(id);
		autorRepository.deleteAutorById(id);
		return autorDTO;
	}*/
}
