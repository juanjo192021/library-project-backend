package com.library.project.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.web.models.Genero;

import java.util.List;

public interface IGeneroRepository extends JpaRepository<Genero, Long>{
}
