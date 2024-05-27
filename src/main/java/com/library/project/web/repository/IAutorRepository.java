package com.library.project.web.repository;

import com.library.project.web.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.project.web.models.Autor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    boolean existsByNombre(String nombre);
    boolean existsByApellidoPaterno(String apellidoPaterno);
    boolean existsByApellidoMaterno(String apellidoMaterno);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM detalle_autor WHERE autor_id = :autorId", nativeQuery = true)
    void deleteDetalleAutorByAutorId(Long autorId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM autor WHERE id = :autorId", nativeQuery = true)
    void deleteAutorById(Long autorId);
}
