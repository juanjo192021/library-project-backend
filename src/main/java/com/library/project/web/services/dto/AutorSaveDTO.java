package com.library.project.web.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AutorSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private List<Long> genero;
}
