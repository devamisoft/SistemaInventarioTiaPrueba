package com.devamisoft.SistemaInventario.dtos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpresaDTO {
    private Long idEmpresa;

    @NotBlank(message = "El RUC es requerido")
    private String ruc;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    private String nombreComercial;
    private String direccion;
    private String telefono;
    private String correo;
    private LocalDateTime createdAt;
    private Integer estado;
    private List<UsuariosDTO> usuarios;
}