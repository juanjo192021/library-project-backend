package com.library.project.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.project.web.models.Seccion;

@Repository
public interface ISeccionRepository extends JpaRepository<Seccion, Long>{

}
