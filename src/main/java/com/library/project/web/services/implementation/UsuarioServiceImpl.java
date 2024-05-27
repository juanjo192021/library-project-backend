package com.library.project.web.services.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.exception.ResourceNotFoundException;
import com.library.project.web.models.Rol;
import com.library.project.web.models.Usuario;
import com.library.project.web.repository.IRolRepository;
import com.library.project.web.repository.IUsuarioRepository;
import com.library.project.web.services.IUsuarioService;
import com.library.project.web.services.dto.UsuarioDTO;
import com.library.project.web.services.dto.UsuarioSaveDTO;
import com.library.project.web.services.dto.UsuarioUpdateDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService{
	
	private ModelMapper mapper = new ModelMapper();

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IRolRepository rolRepository;
	
	@Override
	public List<Usuario> getListUsuarios(){		
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> buscarPorCorreo(String nombre) {
		return usuarioRepository.findByCorreo(nombre);
	}
	
	@Override
	public UsuarioDTO buscarPorId(Long id) {
		Usuario usuario =  usuarioRepository.findById(id).orElse(null);
		UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
		
		return usuarioDTO;
	}
	
	@Override
	public UsuarioDTO guardar(UsuarioSaveDTO usuarioSaveDTO) {
		
		Usuario usuarioModel = mapper.map(usuarioSaveDTO, Usuario.class);
		Rol rolModel = rolRepository.findById(usuarioSaveDTO.getRol()).orElse(null);
				
		usuarioModel.setRol(rolModel);
		
		Usuario usuarioSave = usuarioRepository.save(usuarioModel);

		UsuarioDTO usuarioDTO = mapper.map(usuarioSave, UsuarioDTO.class);
		usuarioDTO.setRol(usuarioSave.getRol().getNombre());
		
		return usuarioDTO;
	}
	
	@Override
	public UsuarioDTO update(UsuarioUpdateDTO usuarioUpdateDTO){
		Usuario usuario = usuarioRepository.findById(usuarioUpdateDTO.getId()).
				orElseThrow(() -> new ResourceNotFoundException("usuario", "id", usuarioUpdateDTO.getId()));
		
		Rol rolModel = rolRepository.findById(usuarioUpdateDTO.getRol()).orElse(null);

		
		if(usuarioUpdateDTO.getNombre() != null) {
			usuario.setNombre(usuarioUpdateDTO.getNombre()); 
		}
		
		if(usuarioUpdateDTO.getApellido_paterno() != null) {
			usuario.setApellido_paterno(usuarioUpdateDTO.getApellido_paterno()); 
		}
		
		if(usuarioUpdateDTO.getApellido_materno() != null) {
			usuario.setApellido_materno(usuarioUpdateDTO.getApellido_materno()); 
		}
		
		if(usuarioUpdateDTO.getCorreo() != null) {
			usuario.setCorreo(usuarioUpdateDTO.getCorreo()); 
		}
		
		if(usuarioUpdateDTO.getRol() != null) {
			usuario.setRol(rolModel); 
		}

		Usuario usuarioUdpate = usuarioRepository.save(usuario);
		UsuarioDTO usuarioDTO = mapper.map(usuarioUdpate, UsuarioDTO.class);
		usuarioDTO.setRol(usuarioUdpate.getRol().getNombre());
		return usuarioDTO;
	}

	@Override
	public void eliminar(Long id) {
		usuarioRepository.deleteById(id);	
	}
}
