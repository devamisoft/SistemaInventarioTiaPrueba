package com.devamisoft.SistemaInventario.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalProductoDTO {
    private Long idLocalProducto;

    @NotNull(message = "El ID del local es requerido")
    private Long localId;

    @NotNull(message = "El ID del producto es requerido")
    private Long productoId;

    @NotNull(message = "El stock es requerido")
    @Positive(message = "El stock debe ser mayor a 0")
    private Integer stock;

    private String nombreLocal;
    private String codigoLocal;

    private String nombreProducto;
    private String codigoProducto;
    private Double precio;

    private LocalDateTime createdAt;

    private String tipoMovimiento;
    private String observacion;
}