package com.library.project.web.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.project.web.models.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AutorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private List<Genero> genero;
}
