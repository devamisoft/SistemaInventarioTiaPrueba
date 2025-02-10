package com.devamisoft.SistemaInventario.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class UsuariosDTO {
    private Long idUsuarios;

    @NotBlank(message = "El usuario es requerido")
    private String usuario;

    @NotBlank(message = "El password es requerido")
    private String password;

    private String correo;
    private String nombreCompleto;
    private LocalDateTime createdAt;
    private Integer estado;

    private Long empresaId;
    private Long rolId;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UsuarioResponseDTO {
        private Long idUsuarios;
        private String usuario;
        private String correo;
        private String nombreCompleto;
        private LocalDateTime createdAt;
        private Integer estado;
        private Long empresaId;
        private Long rolId;
        private String empresaNombre;
        private String rolNombre;
    }
}