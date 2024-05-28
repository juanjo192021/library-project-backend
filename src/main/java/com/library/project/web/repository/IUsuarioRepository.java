package com.library.project.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.project.web.models.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByCorreo(String correo);
	
}
