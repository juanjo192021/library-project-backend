package com.library.project.web.services.implementation;

import java.util.List;

import com.library.project.web.models.Genero;
import com.library.project.web.models.Libro;
import com.library.project.web.repository.IGeneroRepository;
import com.library.project.web.services.dto.AutorDTO;
import com.library.project.web.services.dto.AutorSaveDTO;
import com.library.project.web.services.dto.AutorUpdateDTO;
import com.library.project.web.services.dto.LibroDTO;
import jakarta.persistence.EntityNotFoundException;
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
	public List<Autor> getListAutores(){
		return autorRepository.findAll();
	}

	@Override
	public AutorDTO buscarPorId(Long id){
		Autor autor =  autorRepository.findById(id).orElse(null);
		return autor != null ? mapper.map(autor, AutorDTO.class) : null;
	}

	@Override
	public AutorDTO guardar(AutorSaveDTO autorSaveDTO){
		Autor autorModel = new Autor();
		Genero generoModel = generoRepository.findById(autorSaveDTO.getGenero()).orElse(null);
		autorModel.setNombre(autorSaveDTO.getNombre());
		autorModel.setApellidoPaterno(autorSaveDTO.getApellidoPaterno());
		autorModel.setApellidoMaterno(autorSaveDTO.getApellidoMaterno());
		autorModel.setGenero(generoModel);

		Autor autorSave = autorRepository.save(autorModel);

		// mapper (convierte) el objeto autorSave a un objeto de tipo AutorDTO
		AutorDTO autorDTO = mapper.map(autorSave, AutorDTO.class);
		autorDTO.setGenero(autorSave.getGenero().getNombre());

		return autorDTO;
	}

	@Override
	public AutorDTO delete(Long id){
		Autor autor	= autorRepository.findById(id).orElse(null);
		if (autor == null){
			throw new EntityNotFoundException("AUTOR NOT FOUND IN THE DATABASE");
		}
		AutorDTO autorDTO = mapper.map(autor, AutorDTO.class);
		autorDTO.setGenero(autor.getGenero().getNombre());
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
