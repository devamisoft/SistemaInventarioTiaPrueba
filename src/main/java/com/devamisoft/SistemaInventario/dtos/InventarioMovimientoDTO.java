package com.devamisoft.SistemaInventario.dtos;

import com.devamisoft.SistemaInventario.models.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventarioMovimientoDTO {
    private Long idInventarioMovimiento;
    private TipoMovimiento tipoMovimiento;
    private Integer cantidad;
    private LocalDateTime createdAt;

    // Relaciones
    private LocalDTO local;
    private ProductoDTO producto;
    private UsuariosDTO usuario;
}