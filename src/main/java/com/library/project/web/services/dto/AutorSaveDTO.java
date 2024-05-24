package com.library.project.web.services.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;

@Data
public class AutorSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Required Field")
    private String nombre;
    @NotEmpty(message = "Required Field")
    private String apellidoPaterno;
    @NotEmpty(message = "Required Field")
    private String apellidoMaterno;
    @Positive(message = "Value must be greater than zero")
    private Long genero;
}
