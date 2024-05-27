package com.library.project.web.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public Pasillo guardar(String pasillo) {
		Pasillo pasilloModel = Pasillo.builder().nombre(pasillo).build();
		return pasilloRepository.save(pasilloModel);
	}
	
	//Parametro pasillo: id = id buscado en la bd, nombre = nombre que se va a actualizar
	@Override
	public Pasillo update(Pasillo pasillo) {
		Pasillo pasilloModel = pasilloRepository.findById(pasillo.getId()).orElse(null);
		pasilloModel.setNombre(pasillo.getNombre());
		return pasilloRepository.save(pasilloModel);
	}
	
	@Override
	public void deletePasillo(Long id) {
        pasilloRepository.deleteById(id);
    }
}
