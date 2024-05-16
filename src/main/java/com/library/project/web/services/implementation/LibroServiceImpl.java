package com.library.project.web.services.implementation;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Autor;
import com.library.project.web.models.Libro;
import com.library.project.web.models.Pasillo;
import com.library.project.web.repository.IAutorRepository;
import com.library.project.web.repository.ILibroRepository;
import com.library.project.web.repository.IPasilloRepository;
import com.library.project.web.services.ILibroService;
import com.library.project.web.services.dto.LibroDTO;
import com.library.project.web.services.dto.LibroSaveDTO;

@Service
public class LibroServiceImpl implements ILibroService{
	
	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private ILibroRepository libroRepository;
	
	@Autowired
	private IAutorRepository autorRepository;
	
	@Autowired
	private IPasilloRepository pasilloRepository;
	
	@Override
	public List<Libro> getListLibros(){
		return libroRepository.findAll();
	}
	
	@Override
	public LibroDTO buscarPorId(Long id) {
		Libro libro =  libroRepository.findById(id).orElse(null);
		LibroDTO libroDTO = mapper.map(libro, LibroDTO.class);
		
		return libroDTO;
	}
	
	@Override
	public LibroDTO guardar(LibroSaveDTO libroSaveDTO) {
		
		Libro LibroModel = new Libro();
		Autor autorModel = autorRepository.findById(libroSaveDTO.getAutor()).orElse(null);
		Pasillo pasilloModel = pasilloRepository.findById(libroSaveDTO.getPasillo()).orElse(null);
				
		LibroModel.setTitulo(libroSaveDTO.getTitulo());
		LibroModel.setStock(libroSaveDTO.getStock());
		LibroModel.setAutor(autorModel);
		LibroModel.setPasillo(pasilloModel);
		LibroModel.setFechaPublic(new Date());
		
		Libro LibroSave = libroRepository.save(LibroModel);

		LibroDTO libroDTO = mapper.map(LibroSave, LibroDTO.class);
		libroDTO.setAutor(LibroSave.getAutor().getNombre());
		libroDTO.setPasillo(LibroSave.getPasillo().getNombre());
		
		return libroDTO;
	}

	@Override
	public void eliminar(Long id) {
		libroRepository.deleteById(id);	
	}
	

}
