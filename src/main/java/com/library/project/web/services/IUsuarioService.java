package com.library.project.web.services;

import java.util.List;
import java.util.Optional;

import com.library.project.web.models.Usuario;
import com.library.project.web.services.dto.UsuarioDTO;
import com.library.project.web.services.dto.UsuarioSaveDTO;
import com.library.project.web.services.dto.UsuarioUpdateDTO;

public interface IUsuarioService {

	public List<Usuario> getListUsuarios();

	public Optional<Usuario> buscarPorCorreo(String username);

	public UsuarioDTO findById(Long id);

	public UsuarioDTO delete(Long id);

	public UsuarioDTO update(UsuarioUpdateDTO usuarioUpdateDTO);

	public UsuarioDTO save(UsuarioSaveDTO usuarioSaveDTO);

}
