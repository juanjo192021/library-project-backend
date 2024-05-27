package com.library.project.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.web.models.Genero;

public interface IGeneroRepository extends JpaRepository<Genero, Long>{
	
    public List<Genero> findAllById(Iterable<Long> ids);

}
