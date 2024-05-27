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

import com.library.project.web.services.IUsuarioService;
import com.library.project.web.services.dto.UsuarioDTO;
import com.library.project.web.services.dto.UsuarioSaveDTO;
import com.library.project.web.services.dto.UsuarioUpdateDTO;

import lombok.RequiredArgsConstructor;

import com.library.project.web.models.Usuario;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/usuarios/")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("listar")
	public ResponseEntity<Object> listUsuarios() {
		try {
			List<Usuario> response = this.usuarioService.getListUsuarios();
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Object> idUsuario(@PathVariable Long id) {
		try {
			UsuarioDTO response = this.usuarioService.buscarPorId(id);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioSaveDTO usuario) {
		try {
			UsuarioDTO response = this.usuarioService.guardar(usuario);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

	@PatchMapping("/update")
	public ResponseEntity<Object> updateUsuario(@RequestBody UsuarioUpdateDTO usuario) {
		try {
			UsuarioDTO response = this.usuarioService.update(usuario);	
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}	
	}

}
