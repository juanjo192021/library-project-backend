package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Pasillo;

public interface IPasilloService {

	public List<Pasillo> getListPasillo();

	public Pasillo buscarPorId(Long id);
	
	public Pasillo guardar(String pasillo);

	public void deletePasillo(Long id);

	public Pasillo update(Pasillo pasillo);


}
