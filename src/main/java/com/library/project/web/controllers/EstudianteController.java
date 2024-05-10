package com.library.project.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.web.models.Estudiante;
import com.library.project.web.services.IEstudianteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteServiceImpl;
	
	@GetMapping("/getAllEstudiantes")
	public ResponseEntity<Object> getAllEstudiantes() {
		try {
			
			List<Estudiante> response = this.estudianteServiceImpl.getListEstudiantes();
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
}
