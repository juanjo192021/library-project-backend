package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Prestamo;
import com.library.project.web.services.dto.PrestamoDTO;
import com.library.project.web.services.dto.PrestamoSaveDTO;
import com.library.project.web.services.dto.PrestamoUpdateDTO;

public interface IPrestamoService {

	public List<Prestamo> getListPrestamos();

	public PrestamoDTO findById(Long id);

	public PrestamoDTO save(PrestamoSaveDTO prestamoSaveDTO);

	public PrestamoDTO update(PrestamoUpdateDTO prestamoUpdateDTO);

}
