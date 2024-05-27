package com.library.project.web.services.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AutorSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Iterable<Long> generos;
}
