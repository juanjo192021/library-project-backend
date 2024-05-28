package com.library.project.web.services.implementation;

import java.util.*;

import com.library.project.web.exception.*;
import com.library.project.web.models.Genero;
import com.library.project.web.repository.IGeneroRepository;
import com.library.project.web.services.dto.AutorDTO;
import com.library.project.web.services.dto.AutorSaveDTO;
import com.library.project.web.services.dto.AutorUpdateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Autor;
import com.library.project.web.repository.IAutorRepository;
import com.library.project.web.services.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService{

	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private IAutorRepository autorRepository;

	@Autowired
	private IGeneroRepository generoRepository;
	
	@Override
	public List<Autor> getAll(){ return autorRepository.findAll(); }

	@Override
	public AutorDTO findById(Long id) {
		Autor autor = autorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", id));
		AutorDTO autorDTO = mapper.map(autor, AutorDTO.class);
		autor.setGeneros(autor.getGeneros());
		return autorDTO;
	}

	@Override
	public AutorDTO save(AutorSaveDTO autorSaveDTO) {
		checkDuplicate(autorSaveDTO);
		
		Autor autorModel = mapper.map(autorSaveDTO, Autor.class);
		List<Genero> generosModel = generoRepository.findAllById(autorSaveDTO.getGeneros());
		autorModel.setGeneros(generosModel);
		Autor autorSave = autorRepository.save(autorModel);
		AutorDTO autorDTO = mapper.map(autorSave, AutorDTO.class);
		autorDTO.setGeneros(autorSave.getGeneros());

		return autorDTO;
	}

	private void checkDuplicate(AutorSaveDTO autorSaveDTO) {
		if (autorRepository.existsByNombre(autorSaveDTO.getNombre()) &&
				autorRepository.existsByApellidoPaterno(autorSaveDTO.getApellidoPaterno()) &&
				autorRepository.existsByApellidoMaterno(autorSaveDTO.getApellidoMaterno())) {

			throw new ConflictException("author", "name", autorSaveDTO.getNombre() + " " +
					autorSaveDTO.getApellidoPaterno() + " " +
					autorSaveDTO.getApellidoMaterno());
		}
	}

	@Override
	public AutorDTO delete(Long id){
		Autor autor = autorRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", id));
		AutorDTO autorDTO = mapper.map(autor, AutorDTO.class);
		autorDTO.setGeneros(autor.getGeneros());
		autorRepository.deleteById(id);
		return autorDTO;
	}

	@Override
	public AutorDTO update(AutorUpdateDTO autorUpdateDTO){
		Autor autor = autorRepository.findById(autorUpdateDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("autor", "id", autorUpdateDTO.getId()));
		mapper.map(autorUpdateDTO, autor);
		List<Genero> generosModel = generoRepository.findAllById(autorUpdateDTO.getGeneros());
		autor.setGeneros(generosModel);
		Autor autorUdpate = autorRepository.save(autor);
		AutorDTO autorDTO = mapper.map(autorUdpate, AutorDTO.class);
		autorDTO.setGeneros(autorUdpate.getGeneros());
		return  autorDTO;
	}
}
