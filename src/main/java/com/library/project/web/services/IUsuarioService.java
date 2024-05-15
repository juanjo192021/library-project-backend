package com.library.project.web.services;

import java.util.List;
import java.util.Optional;

import com.library.project.web.models.Usuario;
import com.library.project.web.services.dto.UsuarioDTO;

public interface IUsuarioService {

	public List<Usuario> getListUsuarios();

	public Optional<Usuario> buscarPorCorreo(String username);

	public UsuarioDTO buscarPorId(int id);

}
