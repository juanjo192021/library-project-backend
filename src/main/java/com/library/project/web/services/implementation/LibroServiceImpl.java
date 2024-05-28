package com.library.project.web.services.implementation;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.exception.ResourceNotFoundException;
import com.library.project.web.models.Autor;
import com.library.project.web.models.Carrera;
import com.library.project.web.models.Estudiante;
import com.library.project.web.models.Libro;
import com.library.project.web.models.Pasillo;
import com.library.project.web.repository.IAutorRepository;
import com.library.project.web.repository.ILibroRepository;
import com.library.project.web.repository.IPasilloRepository;
import com.library.project.web.services.ILibroService;
import com.library.project.web.services.dto.EstudianteDTO;
import com.library.project.web.services.dto.EstudianteUpdateDTO;
import com.library.project.web.services.dto.LibroDTO;
import com.library.project.web.services.dto.LibroSaveDTO;
import com.library.project.web.services.dto.LibroUpdateDTO;

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
	public List<Libro> getAll(){
		return libroRepository.findAll();
	}
	
	@Override
	public LibroDTO findById(Long id) {
		Libro libro =  libroRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("libro", "id", id));
		LibroDTO libroDTO = mapper.map(libro, LibroDTO.class);
		return libroDTO;
	}
	
	@Override
	public LibroDTO save(LibroSaveDTO libroSaveDTO) {
		
		Autor autorModel = autorRepository.findById(libroSaveDTO.getAutor()).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", libroSaveDTO.getAutor()));
		Pasillo pasilloModel = pasilloRepository.findById(libroSaveDTO.getPasillo()).
				orElseThrow(() -> new ResourceNotFoundException("pasillo", "id", libroSaveDTO.getPasillo()));
		
		Libro LibroModel =  mapper.map(libroSaveDTO, Libro.class);
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
	public LibroDTO update(LibroUpdateDTO libroUpdateDTO){
		Libro libro = libroRepository.findById(libroUpdateDTO.getId()).
				orElseThrow(() -> new ResourceNotFoundException("libro", "id", libroUpdateDTO.getId()));

		libro.setTitulo(libroUpdateDTO.getTitulo());
		libro.setFechaPublic(libroUpdateDTO.getFechaPublic());
		libro.setStock(libroUpdateDTO.getStock());

		Autor autorModel = autorRepository.findById(libroUpdateDTO.getAutor()).
				orElseThrow(() -> new ResourceNotFoundException("autor", "id", libroUpdateDTO.getAutor()));;
		libro.setAutor(autorModel);

		Pasillo pasilloModel = pasilloRepository.findById(libroUpdateDTO.getPasillo()).
				orElseThrow(() -> new ResourceNotFoundException("pasillo", "id", libroUpdateDTO.getPasillo()));
		libro.setPasillo(pasilloModel);

		Libro libroUdpate = libroRepository.save(libro);
		LibroDTO libroDTO = mapper.map(libroUdpate, LibroDTO.class);
		libroDTO.setAutor(libroUdpate.getAutor().getNombre());
		libroDTO.setPasillo(libroUdpate.getPasillo().getNombre());
		return libroDTO;
	}

	@Override
	public LibroDTO delete(Long id) {
		libroRepository.deleteById(id);
		return null;
	}
}
