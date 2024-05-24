package com.library.project.web.services.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AutorUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Required Field")
    private String nombre;
    @NotEmpty(message = "Required Field")
    private String apellidoPaterno;
    @NotEmpty(message = "Required Field")
    private String apellidoMaterno;
}
