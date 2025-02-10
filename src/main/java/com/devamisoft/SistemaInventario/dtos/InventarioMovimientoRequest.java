package com.devamisoft.SistemaInventario.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventarioMovimientoRequest {
    @NotNull(message = "El ID del local es requerido")
    @Positive(message = "El ID del local debe ser válido")
    private Long localId;

    @NotNull(message = "El ID del producto es requerido")
    @Positive(message = "El ID del producto debe ser válido")
    private Long productoId;

    @NotNull(message = "El tipo de movimiento es requerido")
    private String tipoMovimiento;

    @NotNull(message = "La cantidad es requerida")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    private String observacion;

    private Long usuarioId;
}