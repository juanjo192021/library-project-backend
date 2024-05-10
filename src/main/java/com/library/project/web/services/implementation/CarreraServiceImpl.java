package com.library.project.web.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Carrera;
import com.library.project.web.repository.ICarreraRepository;
import com.library.project.web.services.ICarreraService;

@Service
public class CarreraServiceImpl implements ICarreraService {

	@Autowired
	private ICarreraRepository carreraRepository;
	
	@Override
	public List<Carrera> getListCarreras(){		
		return carreraRepository.findAll();
	}
}
