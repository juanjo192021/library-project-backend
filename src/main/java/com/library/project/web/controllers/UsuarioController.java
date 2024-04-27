package com.library.project.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.web.services.IUsuarioService;

import lombok.RequiredArgsConstructor;

import com.library.project.web.models.Usuario;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioServiceImpl;
	
	@GetMapping("/getAllUsuarios")
	public ResponseEntity<Object> getAllUsuarios() {
		try {
			List<Usuario> response = this.usuarioServiceImpl.getListUsuarios();
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

}
