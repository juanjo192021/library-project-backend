package com.library.project.web.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Genero;
import com.library.project.web.models.Pasillo;
import com.library.project.web.repository.IPasilloRepository;
import com.library.project.web.services.IPasilloService;

@Service
public class PasilloServiceImpl implements IPasilloService {

	@Autowired
	private IPasilloRepository pasilloRepository;
	
	@Override
	public List<Pasillo> getListPasillo(){
		return pasilloRepository.findAll();
	}
	
	@Override
	public Pasillo buscarPorId(Long id) {
		return pasilloRepository.findById(id).orElse(null);
	}
}
