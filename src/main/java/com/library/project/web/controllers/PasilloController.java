package com.library.project.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.web.models.Pasillo;
import com.library.project.web.services.IPasilloService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/pasillos/")
public class PasilloController {
	
	@Autowired
	private IPasilloService pasilloService;
	
	@GetMapping("/listar")
	public ResponseEntity<Object> listarPasillos() {
		try {
			List<Pasillo> response = this.pasilloService.getListPasillo();
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/pasillo/{id}")
	public ResponseEntity<Object> idPasillo(@PathVariable Long id) {
		try {
			Pasillo response = this.pasilloService.buscarPorId(id);
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/guardar/{nombre}")
	public ResponseEntity<Object> savePasillo(@PathVariable String pasillo) {
		try {
			Pasillo response = this.pasilloService.guardar(pasillo);
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

	@PatchMapping("/update")
	public ResponseEntity<Object> updatePasillo(@RequestBody Pasillo pasillo) {
		try {
			Pasillo response = this.pasilloService.update(pasillo);	
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}	
	}
}
