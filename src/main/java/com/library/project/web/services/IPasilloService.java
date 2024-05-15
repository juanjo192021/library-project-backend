package com.library.project.web.services;

import java.util.List;

import com.library.project.web.models.Pasillo;

public interface IPasilloService {

	public List<Pasillo> getListPasillo();

	public Pasillo buscarPorId(int id);

}
