package com.library.project.web.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.project.web.models.Prestamo;
import com.library.project.web.repository.IPrestamoRepository;
import com.library.project.web.services.IPrestamoService;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

	@Autowired
	private IPrestamoRepository prestamoRepository;
	
	@Override
	public List<Prestamo> getListPrestamos(){
		return prestamoRepository.findAll();
	}
}
