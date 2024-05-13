package com.library.project.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.project.web.jwt.AuthRequest;
import com.library.project.web.jwt.AuthResponse;
import com.library.project.web.jwt.JwtTokenUtil;
import com.library.project.web.models.Usuario;
import com.library.project.web.services.IUsuarioService;



@RestController
@RequestMapping("/v1/auth/")
public class AccesoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request)
	{
		try {

			Authentication authentication = this.authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));
			System.out.println(authentication);
			
			Optional<Usuario> user = usuarioService.buscarPorCorreo(request.getCorreo());
			
			String accessToken = jwtUtil.generarToken(user.get());
			System.out.println(accessToken);
						
			AuthResponse response = new AuthResponse(request.getCorreo(), accessToken);
			
			return ResponseEntity.ok(response);
			
		} catch (BadCredentialsException e) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
