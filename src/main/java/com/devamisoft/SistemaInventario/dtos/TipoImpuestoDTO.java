package com.devamisoft.SistemaInventario.dtos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TipoImpuestoDTO {
    private Long idImpuesto;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotNull(message = "El porcentaje es requerido")
    private Integer porcentaje;
    private LocalDateTime createdAt;
    private Integer estado;
}