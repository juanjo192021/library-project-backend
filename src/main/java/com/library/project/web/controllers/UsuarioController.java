package com.library.project.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.project.web.services.IUsuarioService;
import com.library.project.web.models.Usuario;

@Controller
@RequestMapping("/Usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioServiceImpl;
	
	@GetMapping("/getUsuarios")
	private String getUsuarios() {
		List<Usuario> usuarios = usuarioServiceImpl.getListUsuarios();
		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println(usuarios.get(i));
		}
		return "/index";
	}

}
