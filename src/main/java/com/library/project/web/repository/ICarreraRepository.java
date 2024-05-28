package com.library.project.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.web.models.Carrera;

public interface ICarreraRepository extends JpaRepository<Carrera, Long> {
    boolean existsByNombre(String nombre);
}
