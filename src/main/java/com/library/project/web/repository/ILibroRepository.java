package com.library.project.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.web.models.Libro;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Integer>{

}
