package com.library.project.web.services.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.exception.BadRequestException;
import com.library.project.web.exception.ResourceNotFoundException;
import com.library.project.web.models.Estudiante;
import com.library.project.web.models.Libro;
import com.library.project.web.models.Prestamo;
import com.library.project.web.models.Usuario;
import com.library.project.web.repository.IEstudianteRepository;
import com.library.project.web.repository.ILibroRepository;
import com.library.project.web.repository.IPrestamoRepository;
import com.library.project.web.repository.IUsuarioRepository;
import com.library.project.web.services.IPrestamoService;
import com.library.project.web.services.dto.PrestamoDTO;
import com.library.project.web.services.dto.PrestamoSaveDTO;
import com.library.project.web.services.dto.PrestamoUpdateDTO;
import com.library.project.web.utilidades.Fijas;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private IPrestamoRepository prestamoRepository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IEstudianteRepository estudianteRepository;
	
	@Autowired
	private ILibroRepository libroRepository;
	
	@Override
	public List<Prestamo> getListPrestamos(){
		return prestamoRepository.findAll();
	}
	
	@Override
	public PrestamoDTO findById(Long id) {
		Prestamo prestamo = prestamoRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("prestamo", "id", id));
		PrestamoDTO prestamoDTO = mapper.map(prestamo, PrestamoDTO.class);
		prestamoDTO.setUsuario(prestamo.getUsuario().getNombre());
		prestamoDTO.setEstudiante(prestamo.getEstudiante().getNombre());
		prestamoDTO.setLibros(prestamo.getLibros().stream()
                .map(Libro::getTitulo)
                .collect(Collectors.toList()));
		return prestamoDTO;
	}

	@Override
	public PrestamoDTO save(PrestamoSaveDTO prestamoSaveDTO) {
		Prestamo prestamo = new Prestamo();
        prestamo.setFechaPrestamo(new Date());
        prestamo.setFechaDevolucion(calcularFechaDevolucion(new Date(),Fijas.DIAS_PRESTAMO));
		Estudiante estudiante = estudianteRepository.findById(prestamoSaveDTO.getEstudiante()).
		orElseThrow(() -> new ResourceNotFoundException("estudiante", "id", prestamoSaveDTO.getEstudiante()));
		prestamo.setEstudiante(estudiante);
		Usuario usuario = usuarioRepository.findById(prestamoSaveDTO.getUsuario()).
				orElseThrow(() -> new ResourceNotFoundException("usuario", "id", prestamoSaveDTO.getUsuario()));
		prestamo.setUsuario(usuario);
		List<Libro> libros = libroRepository.findAllById(prestamoSaveDTO.getLibros());
		prestamo.setLibros(disminuirStockLibro(libros));
		prestamo.setCantidad(libros.size());
		prestamo.setEstado(Fijas.ESTADO_PRESTAMO_PRESTADO);
		Prestamo prestamoSave = prestamoRepository.save(prestamo);
		PrestamoDTO prestamoDTO = mapper.map(prestamoSave, PrestamoDTO.class);
		prestamoDTO.setUsuario(prestamoSave.getUsuario().getNombre());
		prestamoDTO.setEstudiante(prestamoSave.getEstudiante().getNombre());
		prestamoDTO.setLibros(prestamoSave.getLibros().stream()
                .map(Libro::getTitulo)
                .collect(Collectors.toList()));
		return prestamoDTO;
	}
	
	@Override
	public PrestamoDTO update(PrestamoUpdateDTO prestamoUpdateDTO){
		Prestamo prestamo = prestamoRepository.findById(prestamoUpdateDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("prestamo", "id", prestamoUpdateDTO.getId()));
		List<Libro> libros = prestamo.getLibros();
		if(!prestamoUpdateDTO.getLibros().isEmpty()) {
			libros = libroRepository.findAllById(prestamoUpdateDTO.getLibros());
			prestamo.setLibros(libros);
			prestamo.setCantidad(libros.size());
		}
		if(prestamoUpdateDTO.getEstado() != 0) {
			if(prestamoUpdateDTO.getEstado() == Fijas.ESTADO_PRESTAMO_DEVUELTO) {
				aumentarStockLibro(libros);
			}
			prestamo.setEstado(prestamoUpdateDTO.getEstado());
		}
		Prestamo prestamoUdpate = prestamoRepository.save(prestamo);
		PrestamoDTO prestamoDTO = mapper.map(prestamoUdpate, PrestamoDTO.class);
		prestamoDTO.setUsuario(prestamoUdpate.getUsuario().getNombre());
		prestamoDTO.setEstudiante(prestamoUdpate.getEstudiante().getNombre());
		prestamoDTO.setLibros(prestamoUdpate.getLibros().stream()
                .map(Libro::getTitulo)
                .collect(Collectors.toList()));
		return  prestamoDTO;
	}
	
	private List<Libro> disminuirStockLibro(List<Libro> libros) {
		List<Libro> librosModel = new ArrayList<>();
		for (Libro libro : libros) {
	        if (libro.getStock() > 0) {
	            libro.setStock(libro.getStock() - 1);
	            librosModel.add(libro);
	        } else {
	            throw new BadRequestException("El libro con ID " + libro.getId() + " no tiene stock disponible.");
	        }
	    }
		return librosModel;
	}
	
	private void aumentarStockLibro(List<Libro> libros) {
		for (Libro libro : libros) {
			libro.setStock(libro.getStock() + 1);
	    }
	}
	
	private Date calcularFechaDevolucion(Date fechaPrestamo, int diasPrestamo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPrestamo);
        calendar.add(Calendar.DAY_OF_YEAR, diasPrestamo);
        return calendar.getTime();
    }
}
