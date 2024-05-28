package com.library.project.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.web.models.Estudiante;

public interface IEstudianteRepository  extends JpaRepository<Estudiante, Long>{

    boolean existsByNombre(String nombre);

    boolean existsByApellidoPaterno(String apellidoPaterno);

    boolean existsByApellidoMaterno(String apellidoMaterno);

    boolean existsByNumeroDocumento(String numeroDocumento);
}
