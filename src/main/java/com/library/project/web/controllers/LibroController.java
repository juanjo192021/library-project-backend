package com.library.project.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.web.models.Libro;
import com.library.project.web.services.ILibroService;
import com.library.project.web.services.dto.LibroDTO;
import com.library.project.web.services.dto.LibroSaveDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/libros/")
public class LibroController {
	
	@Autowired
	private ILibroService libroService;
	
	@GetMapping("listar")
	public ResponseEntity<Object> getAllLibros() {
		try {
			List<Libro> response = this.libroService.getListLibros();
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Object> usuariosId(@PathVariable Integer id) {
		try {
			LibroDTO response = this.libroService.buscarPorId(id);
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Object> usuariosId(@RequestBody LibroSaveDTO libro) {
		try {
			LibroDTO response = this.libroService.guardar(libro);
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	

}
