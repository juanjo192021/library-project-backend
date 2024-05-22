package com.library.project.web.controllers;

import java.util.List;

import com.library.project.web.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.project.web.models.Autor;
import com.library.project.web.services.IAutorService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/autor/")
public class AutorController {
	
	@Autowired
	private IAutorService autorService;
	
	@GetMapping("getAll")
	public ResponseEntity<Object> getAllAutores() {
		try {
			List<Autor> response = this.autorService.getListAutores();
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			AutorDTO response = this.autorService.buscarPorId(id);

			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Object> saveAutor(@RequestBody AutorSaveDTO autor) {
		try {
			AutorDTO response = this.autorService.guardar(autor);

			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateAutor(@RequestBody AutorUpdateDTO autor) {
		try {
			AutorDTO response = this.autorService.update(autor);

			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteAutor(@PathVariable Long id) {
		try {
			AutorDTO response = this.autorService.delete(id);

			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
}
