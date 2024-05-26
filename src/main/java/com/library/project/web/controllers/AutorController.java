package com.library.project.web.controllers;

import java.util.List;

import com.library.project.web.exception.BadRequestException;
import com.library.project.web.exception.ResourceNotFoundException;
import com.library.project.web.services.dto.*;
import jakarta.validation.Valid;
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
@RequestMapping("/v1/autores/")
public class AutorController {
	
	@Autowired
	private IAutorService autorService;
	@PostMapping("/save")
	public ResponseEntity<AutorDTO> saveAutor(@RequestBody AutorSaveDTO autor){
		AutorDTO response = this.autorService.save(autor);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@GetMapping("getAll")
	public ResponseEntity<Object> getAllAutores() {
		List<Autor> response = autorService.getAll();
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<AutorDTO> findById(@PathVariable Long id) {
		AutorDTO response = this.autorService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@PatchMapping("/update")
	public ResponseEntity<AutorDTO> updateAutor(@RequestBody AutorUpdateDTO autor) {
		AutorDTO response = this.autorService.update(autor);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AutorDTO> deleteAutor(@PathVariable Long id) {
		AutorDTO response = this.autorService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
}
