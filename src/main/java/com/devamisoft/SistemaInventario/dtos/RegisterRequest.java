package com.devamisoft.SistemaInventario.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    @NotBlank(message = "El usuario es requerido")
    private String usuario;

    @NotBlank(message = "El password es requerido")
    private String password;

    @NotBlank(message = "La confirmación del password es requerida")
    private String confirmPassword;

    @Email(message = "Debe ser un correo válido")
    private String correo;

    @NotBlank(message = "El nombre completo es requerido")
    private String nombreCompleto;

    @NotNull(message = "El ID de la empresa es requerido")
    private Long empresaId;

    @NotNull(message = "El ID del rol es requerido")
    private Long rolId;
}